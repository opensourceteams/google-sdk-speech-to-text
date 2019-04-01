package com.opensourceteams.module.google.speech.to.text.simple.async;

import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * - 美国英语 (en-US) 或英国英语 (en-GB)
 * - 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 */
public class RunEnglish {

    static Logger logger = Logger.getLogger(RunEnglish.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
        //10MB 以内
        syncRecognizeFile("data/wav/english/audio.raw");
        logger.info("结束");

    }





    /**
     * Performs speech recognition on raw PCM audio and prints the transcription.
     *
     * @param fileName the path to a PCM audio file to transcribe.
     */
    public static void syncRecognizeFile(String fileName) throws Exception {
        try (com.google.cloud.speech.v1.SpeechClient speech = com.google.cloud.speech.v1.SpeechClient.create()) {
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Configure request with local raw PCM audio
            com.google.cloud.speech.v1.RecognitionConfig config =
                    com.google.cloud.speech.v1.RecognitionConfig.newBuilder()
                            .setEncoding(com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            .setSampleRateHertz(16000)
                            .build();
            com.google.cloud.speech.v1.RecognitionAudio audio = com.google.cloud.speech.v1.RecognitionAudio.newBuilder().setContent(audioBytes).build();

            // Use blocking call to get audio transcript
            com.google.cloud.speech.v1.RecognizeResponse response = speech.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (com.google.cloud.speech.v1.SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
        }
    }

}
