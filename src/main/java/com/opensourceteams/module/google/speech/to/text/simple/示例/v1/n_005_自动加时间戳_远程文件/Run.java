package com.opensourceteams.module.google.speech.to.text.simple.示例.v1.n_005_自动加时间戳_远程文件;


import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;
import com.opensourceteams.module.common.util.print.PrintUtil;

import java.util.List;

//import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

public class Run {
    static String targetLanguage = "cmn-Hans-CN" ;
    static String filePath = "gs://thinktothings.com/example/小狮子.wav" ;
    static int sampleRateHertz = 16000;

    public static void main(String[] args) throws Exception {

        long beginTime = System.currentTimeMillis();


        asyncRecognizeGcs(filePath);
        PrintUtil.printDealTime(beginTime);
    }



    /**
     * Performs non-blocking speech recognition on remote FLAC file and prints the transcription.
     *
     * @param gcsUri the path to the remote LINEAR16 audio file to transcribe.
     */
    public static void asyncRecognizeGcs(String gcsUri) throws Exception {
        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create()) {

            // Configure remote file request for Linear16
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode(targetLanguage)
                            .setSampleRateHertz(sampleRateHertz)
                            .setEnableAutomaticPunctuation(true) //自动加标点符号
                            .setEnableWordTimeOffsets(true) //自动加时间戳
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s\n", alternative.getTranscript());

                for (WordInfo wordInfo : alternative.getWordsList()) {
                    System.out.println(wordInfo.getWord());
                    System.out.printf(
                            "\t%s.%s sec - %s.%s sec  ",
                            wordInfo.getStartTime().getSeconds(),
                            wordInfo.getStartTime().getNanos() / 100000000,
                            wordInfo.getEndTime().getSeconds(),
                            wordInfo.getEndTime().getNanos() / 100000000

                            )

                            ;


                }
            }
        }
    }
}
