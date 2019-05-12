package com.opensourceteams.module.google.speech.to.text.simple.示例.v1.n_002_短语音_wav_格式_远程文件;


import com.google.cloud.speech.v1.*;
import com.opensourceteams.module.common.util.print.PrintUtil;

import java.io.IOException;
import java.util.List;

//import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

public class Run {
    static String targetLanguage = "cmn-Hans-CN" ;
    static String filePath = "gs://thinktothings.com/example/40.wav" ;
    static int sampleRateHertz = 16000;

    public static void main(String[] args) {

        long beginTime = System.currentTimeMillis();


        call();
        PrintUtil.printDealTime(beginTime);
    }


    public static void call(){
        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16;
            // int sampleRateHertz = 44100;
            String languageCode = targetLanguage;
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(encoding)
                    .setSampleRateHertz(sampleRateHertz)
                    .setLanguageCode(languageCode)
                    .build();
            String uri = filePath ;
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(uri)
                    .build();
            RecognizeResponse response = speechClient.recognize(config, audio);

            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
