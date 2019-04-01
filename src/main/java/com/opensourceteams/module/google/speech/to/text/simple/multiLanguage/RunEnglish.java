package com.opensourceteams.module.google.speech.to.text.simple.multiLanguage;

import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * - 美国英语 (en-US) 或英国英语 (en-GB)
 * - 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 */
public class RunEnglish {

    static Logger logger = Logger.getLogger(RunEnglish.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
        transcribeMultiLanguage("data/wav/english/Google_Gnome.wav");
        logger.info("结束");

    }


    /**
     * Transcribe a local audio file with multi-language recognition
     *
     * @param fileName the path to the audio file
     */
    public static void transcribeMultiLanguage(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        // Get the contents of the local audio file
        byte[] content = Files.readAllBytes(path);

        try (SpeechClient speechClient = SpeechClient.create()) {

            RecognitionAudio recognitionAudio =
                    RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();
            ArrayList<String> languageList = new ArrayList<>();
            languageList.add("es-ES");//西班牙语
            languageList.add("en-US");//英语（美国）

            // Configure request to enable multiple languages
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(com.google.cloud.speech.v1p1beta1.RecognitionConfig.AudioEncoding.LINEAR16)
                            .setSampleRateHertz(16000)
                            .setLanguageCode("ja-JP")
                            .addAllAlternativeLanguageCodes(languageList)
                            .build();
            // Perform the transcription request
            RecognizeResponse recognizeResponse = speechClient.recognize(config, recognitionAudio);

            // Print out the results
            for (SpeechRecognitionResult result : recognizeResponse.getResultsList()) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternatives(0);
                System.out.format("Transcript : %s\n\n", alternative.getTranscript());
            }
        }
    }


}
