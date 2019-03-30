package com.opensourceteams.module.google.speech.to.text.simple;

import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * 语言支持 : https://cloud.google.com/speech-to-text/docs/languages
 * 普通話 (香港)	cmn-Hans-HK	中文、普通话（香港简体）
 * 廣東話 (香港)	yue-Hant-HK	中文、粤语（香港繁体）
 * 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 *
 * English (Great Britain)	en-GB	英语（英国）
 * English (United States)	en-US	英语（美国）
 */
public class SpeechToTextTimeRun {


    private static Log log = LogFactory.getLog(SpeechToTextTimeRun.class);

    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     */
    public static void main(String... args) throws Exception {


        log.info("开始");
        // Instantiates a client

        try (SpeechClient speechClient = SpeechClient.create()) {

            // The path to the audio file to transcribe
           // String fileName = "/Users/liuwen/Downloads/temp/语音测试文件/录音-20秒.wav";
            String fileName = "data/wav/早饭吃西红柿炒鸡蛋.wav";

            // Reads the audio file into memory
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Builds the sync recognize request
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    //.setEncoding(AudioEncoding.LINEAR16)
                    //.setSampleRateHertz(16000)
                    .setLanguageCode("cmn-Hans-CN")
                    .setEnableWordTimeOffsets(true)
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                //System.out.printf("Transcription: %s%n", alternative.getTranscript());
                System.out.println(alternative.getTranscript());


                System.out.println("开始时间戳");
                for(com.google.cloud.speech.v1p1beta1.WordInfo wordInfo : alternative.getWordsList()){

                    System.out.println(wordInfo.getWord());
                    System.out.printf(
                            "\t%s.%s sec - %s.%s sec\n",
                            wordInfo.getStartTime().getSeconds(),
                            wordInfo.getStartTime().getNanos() / 1000000,
                            wordInfo.getEndTime().getSeconds(),
                            wordInfo.getEndTime().getNanos() / 1000000);

                    System.out.println();
                }


            }
        }

        log.info("结束");
    }
}