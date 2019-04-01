package com.opensourceteams.module.google.speech.to.text.simple.wordLevelConf;

import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * - 美国英语 (en-US) 或英国英语 (en-GB)
 * - 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 */
public class RunEnglish {

    static Logger logger = Logger.getLogger(RunEnglish.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
        transcribeWordLevelConfidence("data/wav/english/audio.raw");
        logger.info("结束");

    }




    /**
     * Transcribe a local audio file with word level confidence
     *
     * @param fileName the path to the local audio file
     */
    public static void transcribeWordLevelConfidence(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        byte[] content = Files.readAllBytes(path);

        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionAudio recognitionAudio =
                    RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();
            // Configure request to enable word level confidence
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setSampleRateHertz(16000)
                            .setLanguageCode("en-US")
                            .setEnableWordConfidence(true)
                            .build();
            // Perform the transcription request
            RecognizeResponse recognizeResponse = speechClient.recognize(config, recognitionAudio);

            // Print out the results
            for (SpeechRecognitionResult result : recognizeResponse.getResultsList()) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternatives(0);
                System.out.format("Transcript : %s\n", alternative.getTranscript());
                System.out.format(
                        "First Word and Confidence : %s %s \n",
                        alternative.getWords(0).getWord(), alternative.getWords(0).getConfidence());
            }
        }
    }

}
