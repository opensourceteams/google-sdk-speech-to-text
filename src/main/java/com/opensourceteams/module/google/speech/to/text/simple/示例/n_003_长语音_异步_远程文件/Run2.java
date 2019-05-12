package com.opensourceteams.module.google.speech.to.text.simple.示例.n_003_长语音_异步_远程文件;


// Imports the Google Cloud client library

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig.AudioEncoding;

import java.util.List;


public class Run2 {


    static String fileName = "gs://thinktothings.com/example/big/59-m.wav";
    static String targetLanguage = "cmn-Hans-CN" ;

    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     */
    public static void main(String... args) throws Exception {

        long beginTime = System.currentTimeMillis() ;
        asyncRecognizeGcs(fileName);

        printDealTime(beginTime);



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
                            .setEncoding(AudioEncoding.LINEAR16)
                            .setLanguageCode(targetLanguage)
                            .setSampleRateHertz(16000)
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
            }
        }
    }


    /**
     * 打印执行的时间
     * @param beginTime
     */
    public static void printDealTime(long beginTime){
        System.out.print(System.currentTimeMillis() - beginTime  + "(毫秒) ");
        System.out.println((System.currentTimeMillis() - beginTime)/1000  + "(秒)");
    }


}
