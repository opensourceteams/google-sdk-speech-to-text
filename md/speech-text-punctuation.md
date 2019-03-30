# google 语音转文本自动加标点符号(短语音)
- 经测试不是很给力，有些没断句，还有丢语句的现象
- 同步识别请求仅限于持续时间不超过 1 分钟的音频数据，异步请求可用于任何持续时间不超过 180 分钟的音频数据
- 将语音转化成文本

## 源码
- https://github.com/opensourceteams/google-sdk-speech-to-text

### 调用示例
```aidl
package com.opensourceteams.module.google.speech.to.text.simple;

import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 语言支持 : https://cloud.google.com/speech-to-text/docs/languages
 * 普通話 (香港)	cmn-Hans-HK	中文、普通话（香港简体）
 * 廣東話 (香港)	yue-Hant-HK	中文、粤语（香港繁体）
 * 普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
 *
 * English (Great Britain)	en-GB	英语（英国）
 * English (United States)	en-US	英语（美国）
 */
public class SpeechToTextPunctuationRun {



    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     * 官网文档：https://cloud.google.com/speech-to-text/docs/automatic-punctuation?hl=zh-cn
     */
    public static void main(String... args) throws Exception {


        String fileName = "data/wav/english/Google_Gnome.wav";
        Path path = Paths.get(fileName);
        byte[] content = Files.readAllBytes(path);

        try (SpeechClient speechClient = SpeechClient.create()) {
            // Configure request with local raw PCM audio
            RecognitionConfig recConfig =
                    RecognitionConfig.newBuilder()
                            //.setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            //.setSampleRateHertz(48000)
                            .setEnableAutomaticPunctuation(true)
                            .build();

            // Get the contents of the local audio file
            RecognitionAudio recognitionAudio =
                    RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(content)).build();

            // Perform the transcription request
            RecognizeResponse recognizeResponse = speechClient.recognize(recConfig, recognitionAudio);

            // Just print the first result here.
            SpeechRecognitionResult result = recognizeResponse.getResultsList().get(0);

            // There can be several alternative transcripts for a given chunk of speech. Just use the
            // first (most likely) one here.
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);

            // Print out the result
            System.out.printf("Transcript : %s\n", alternative.getTranscript());
        }

    }
}
```
### 结果
```aidl
OK Google stream stranger things from Netflix to my TV. Okay, stranger things from Netflix from the people that brought you Google home comes the next evolution of a smart home and it's just outside your window me Google Now by how can I help? Okay, he's right. Okay, no turn on the hose. I'm holding. Sure.

```