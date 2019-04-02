# google sdk speech-to-text

- 同步识别（REST 和 gRPC）将音频数据发送到 Speech-to-Text API，对该数据执行识别，并在所有音频处理完毕后返回结果。同步识别请求仅限于持续时间不超过 1 分钟的音频数据。

- 异步识别（REST 和 gRPC）将音频数据发送到 Speech-to-Text API 并启动长时间运行的操作。使用此操作，您可以定期轮询识别结果。异步请求可用于任何持续时间不超过 180 分钟的音频数据。

- 流式识别（仅限 gRPC）对 gRPC 双向流内提供的音频数据执行识别。流式传输请求专为实时识别（例如从麦克风采集实时音频）而设计。流式识别可以一边采集音频一边提供临时结果，例如实现在用户仍在讲话时显示结果。

## 源码
- https://github.com/opensourceteams/google-sdk-speech-to-text



## 识别率超高的在线麦克风语音转文本(免费工具)
- https://www.speechtexter.com

## 官网文档
- https://cloud.google.com/speech-to-text/docs/quickstart-client-libraries#client-libraries-usage-java

## Java 流式和非流式语音识别示例(官网示例)
- https://github.com/GoogleCloudPlatform/java-docs-samples
- https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/speech

### google speech-to-text
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/languages.md

### google 语音转文本(短语音)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text.md

### google 语音转文本(英文短语音)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-english.md


### google 语音转文本(异步短语音)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-async.md


### google 语音转文本(每个单词带时间戳)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-time.md

### google 语音转文本自动加标点符号(短语音)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/Google_Gnome.wav

### google 通过流的方式发送语音文件转文本
-  https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-steam.md

### google 通过流的方式发送语音(直接用电脑上的麦克风说话)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-micsteam.md

### google 语音转文本(多轨支持)
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-multiChannel.md

### Google Speech 多语言支持
- https://github.com/opensourceteams/google-sdk-speech-to-text/blob/master/md/speech-text-multiLanguage.md