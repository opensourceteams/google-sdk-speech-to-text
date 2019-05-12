package com.opensourceteams.module.google.speech.to.text.simple.示例.v1.n_005_短语音_自动加时间戳_本地文件;


import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.opensourceteams.module.common.util.print.PrintUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

public class Run {
    static String targetLanguage = "cmn-Hans-CN" ;
    static String filePath = "data/wav/cn/早饭吃西红柿炒鸡蛋.wav" ;
    static int sampleRateHertz = 16000;

    public static void main(String[] args) throws Exception {

        long beginTime = System.currentTimeMillis();


        syncRecognizeFile(filePath);
        PrintUtil.printDealTime(beginTime);
    }


    /**
     * Performs speech recognition on raw PCM audio and prints the transcription.
     *
     * @param fileName the path to a PCM audio file to transcribe.
     */
    public static void syncRecognizeFile(String fileName) throws Exception {
        try (SpeechClient speech = SpeechClient.create()) {
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Configure request with local raw PCM audio
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode(targetLanguage)
                            .setSampleRateHertz(16000)
                            .setEnableAutomaticPunctuation(true) //自动加标点符号
                            .setEnableWordTimeOffsets(true) //自动加时间戳
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

            // Use blocking call to get audio transcript
            RecognizeResponse response = speech.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());


                for (WordInfo wordInfo : alternative.getWordsList()) {
                    System.out.println(wordInfo.getWord());
                    System.out.printf(
                            "\t%s.%s sec - %s.%s sec  执行时间:%s 毫秒 ",
                            wordInfo.getStartTime().getSeconds(),
                            wordInfo.getStartTime().getNanos() / 100000000,
                            wordInfo.getEndTime().getSeconds(),
                            wordInfo.getEndTime().getNanos() / 100000000,
                            wordInfo.getEndTime().getSeconds() * 10 + wordInfo.getEndTime().getNanos()/ 100000000 - (wordInfo.getStartTime().getSeconds() *10 +wordInfo.getStartTime().getNanos()/ 100000000)

                    );
                }
            }
        }
    }
}
