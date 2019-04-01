# Google Speech 多语言支持
- 即单轨声音中，支持多种语言对话，都能翻译出来


## 源码
- https://github.com/opensourceteams/google-sdk-speech-to-text

## 示例
```aidl
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
public class RunCN {

    static Logger logger = Logger.getLogger(RunCN.class);
    public static void main(String[] args) throws Exception {
        logger.info("开始");
        transcribeMultiLanguage("data/wav/cn/中英-11秒.wav");
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

            languageList.add( "en-US");//英语（美国）

            // Configure request to enable multiple languages
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                           // .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            //.setSampleRateHertz(16000)
                            .setLanguageCode("cmn-Hans-CN") //中文、普通话（中国简体）
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

```

## 结果
```aidl
Transcript : 你早饭吃什么我早饭吃蛋炒饭Good morning Good afternoon Thank you very much
```

