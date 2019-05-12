package com.opensourceteams.module.google.speech.to.text.simple.示例.n_002_短语音_同步语音识别_远程文件;


// Imports the Google Cloud client library

import com.google.cloud.speech.v1p1beta1.*;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Run {


    static String fileName = "gs://thinktothings.com/example/40.flac";
    static String targetLanguage = "cmn-Hans-CN" ;
    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     */
    public static void main(String... args) throws Exception {

        long beginTime = System.currentTimeMillis() ;
        syncRecognizeGcs(fileName);

        printDealTime(beginTime);



    }


    public static void syncRecognizeGcs(String gcsUri) throws Exception {
        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create()) {
            // Builds the request for remote FLAC file
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(AudioEncoding.FLAC)
                            .setLanguageCode(targetLanguage)
                            .setSampleRateHertz(16000)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Use blocking call for getting audio transcript
            RecognizeResponse response = speech.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
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
