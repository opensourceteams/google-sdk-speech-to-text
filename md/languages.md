# google speech-to-text
- 支持大量的语言和方言
- https://cloud.google.com/speech-to-text/docs/languages

```aidl
语言支持
Cloud Speech-to-Text 使用可读懂多种语言之一的语音识别引擎。这些语言是在识别请求的 languageCode 参数中指定的。每个语言代码参数都包含一个 BCP-47 标识符。这些标记通常采用“language-region”的格式，其中“language”是指主要语言，“region”是指特定方言的区域（通常是国家/地区标识符）。例如，英语可以用美国英语 (en-US) 或英国英语 (en-GB) 表示。

Speech-to-Text 可以识别以下语言的语音。

语言	languageCode	语言（中文名称）
Afrikaans (Suid-Afrika)	af-ZA	南非荷兰语（南非）
አማርኛ (ኢትዮጵያ)	am-ET	阿姆哈拉语（埃塞俄比亚）
Հայ (Հայաստան)	hy-AM	亚美尼亚语（亚美尼亚）
Azərbaycan (Azərbaycan)	az-AZ	阿塞拜疆语（阿塞拜疆）
Bahasa Indonesia (Indonesia)	id-ID	印度尼西亚语（印度尼西亚）
Bahasa Melayu (Malaysia)	ms-MY	马来语（马来西亚）
বাংলা (বাংলাদেশ)	bn-BD	孟加拉语（孟加拉）
বাংলা (ভারত)	bn-IN	孟加拉语（印度）
Català (Espanya)	ca-ES	加泰罗尼亚语（西班牙）
Čeština (Česká republika)	cs-CZ	捷克语（捷克共和国）
Dansk (Danmark)	da-DK	丹麦语（丹麦）
Deutsch (Deutschland)	de-DE	德语（德国）
English (Australia)	en-AU	英语（澳大利亚）
English (Canada)	en-CA	英语（加拿大）
English (Ghana)	en-GH	英语（加纳）
English (Great Britain)	en-GB	英语（英国）
English (India)	en-IN	英语（印度）
English (Ireland)	en-IE	英语（爱尔兰）
English (Kenya)	en-KE	英语（肯尼亚）
English (New Zealand)	en-NZ	英语（新西兰）
English (Nigeria)	en-NG	英语（尼日利亚）
English (Philippines)	en-PH	英语（菲律宾）
English (South Africa)	en-ZA	英语（南非）
English (Tanzania)	en-TZ	英语（坦桑尼亚）
English (United States)	en-US	英语（美国）
Español (Argentina)	es-AR	西班牙语（阿根廷）
Español (Bolivia)	es-BO	西班牙语（玻利维亚）
Español (Chile)	es-CL	西班牙语（智利）
Español (Colombia)	es-CO	西班牙语（哥伦比亚）
Español (Costa Rica)	es-CR	西班牙语（哥斯达黎加）
Español (Ecuador)	es-EC	西班牙语（厄瓜多尔）
Español (El Salvador)	es-SV	西班牙语（萨尔瓦多）
Español (España)	es-ES	西班牙语（西班牙）
Español (Estados Unidos)	es-US	西班牙语（美国）
Español (Guatemala)	es-GT	西班牙语（危地马拉）
Español (Honduras)	es-HN	西班牙语（洪都拉斯）
Español (México)	es-MX	西班牙语（墨西哥）
Español (Nicaragua)	es-NI	西班牙语（尼加拉瓜）
Español (Panamá)	es-PA	西班牙语（巴拿马）
Español (Paraguay)	es-PY	西班牙语（巴拉圭）
Español (Perú)	es-PE	西班牙语（秘鲁）
Español (Puerto Rico)	es-PR	西班牙语（波多黎各）
Español (República Dominicana)	es-DO	西班牙语（多米尼加共和国）
Español (Uruguay)	es-UY	西班牙语（乌拉圭）
Español (Venezuela)	es-VE	西班牙语（委内瑞拉）
Euskara (Espainia)	eu-ES	巴斯克语（西班牙）
Filipino (Pilipinas)	fil-PH	菲律宾语（菲律宾）
Français (Canada)	fr-CA	法语（加拿大）
Français (France)	fr-FR	法语（法国）
Galego (España)	gl-ES	加利西亚语（西班牙）
ქართული (საქართველო)	ka-GE	格鲁吉亚语（格鲁吉亚）
ગુજરાતી (ભારત)	gu-IN	古吉拉特语（印度）
Hrvatski (Hrvatska)	hr-HR	克罗地亚语（克罗地亚）
IsiZulu (Ningizimu Afrika)	zu-ZA	祖鲁语（南非）
Íslenska (Ísland)	is-IS	冰岛语（冰岛）
Italiano (Italia)	it-IT	意大利语（意大利）
Jawa (Indonesia)	jv-ID	爪哇语（印度尼西亚）
ಕನ್ನಡ (ಭಾರತ)	kn-IN	卡纳达语（印度）
ភាសាខ្មែរ (កម្ពុជា)	km-KH	高棉语（柬埔寨）
ລາວ (ລາວ)	lo-LA	老挝语（老挝）
Latviešu (latviešu)	lv-LV	拉脱维亚语（拉脱维亚）
Lietuvių (Lietuva)	lt-LT	立陶宛语（立陶宛）
Magyar (Magyarország)	hu-HU	匈牙利语（匈牙利）
മലയാളം (ഇന്ത്യ)	ml-IN	马拉雅拉姆语（印度）
मराठी (भारत)	mr-IN	马拉地语（印度）
Nederlands (Nederland)	nl-NL	荷兰语（荷兰）
नेपाली (नेपाल)	ne-NP	尼泊尔语（尼泊尔）
Norsk bokmål (Norge)	nb-NO	博克马尔挪威语（挪威）
Polski (Polska)	pl-PL	波兰语（波兰）
Português (Brasil)	pt-BR	葡萄牙语（巴西）
Português (Portugal)	pt-PT	葡萄牙语（葡萄牙）
Română (România)	ro-RO	罗马尼亚语（罗马尼亚）
සිංහල (ශ්රී ලංකාව)	si-LK	僧伽罗语（斯里兰卡）
Slovenčina (Slovensko)	sk-SK	斯洛伐克语（斯洛伐克）
Slovenščina (Slovenija)	sl-SI	斯洛文尼亚语（斯洛文尼亚）
Urang (Indonesia)	su-ID	巽他语（印度尼西亚）
Swahili (Tanzania)	sw-TZ	斯瓦希里语（坦桑尼亚）
Swahili (Kenya)	sw-KE	斯瓦希里语（肯尼亚）
Suomi (Suomi)	fi-FI	芬兰语（芬兰）
Svenska (Sverige)	sv-SE	瑞典语（瑞典）
தமிழ் (இந்தியா)	ta-IN	泰米尔语（印度）
தமிழ் (சிங்கப்பூர்)	ta-SG	泰米尔语（新加坡）
தமிழ் (இலங்கை)	ta-LK	泰米尔语（斯里兰卡）
தமிழ் (மலேசியா)	ta-MY	泰米尔语（马来西亚）
తెలుగు (భారతదేశం)	te-IN	泰卢固语（印度）
Tiếng Việt (Việt Nam)	vi-VN	越南语（越南）
Türkçe (Türkiye)	tr-TR	土耳其语（土耳其）
اردو (پاکستان)	ur-PK	乌尔都语（巴基斯坦）
اردو (بھارت)	ur-IN	乌尔都语（印度）
Ελληνικά (Ελλάδα)	el-GR	希腊语（希腊）
Български (България)	bg-BG	保加利亚语（保加利亚）
Русский (Россия)	ru-RU	俄语（俄罗斯）
Српски (Србија)	sr-RS	塞尔维亚语（塞尔维亚）
Українська (Україна)	uk-UA	乌克兰语（乌克兰）
עברית (ישראל)	he-IL	希伯来语（以色列）
العربية (إسرائيل)	ar-IL	阿拉伯语（以色列）
العربية (الأردن)	ar-JO	阿拉伯语（约旦）
العربية (الإمارات)	ar-AE	阿拉伯语（阿拉伯联合酋长国）
العربية (البحرين)	ar-BH	阿拉伯语（巴林）
العربية (الجزائر)	ar-DZ	阿拉伯语（阿尔及利亚）
العربية (السعودية)	ar-SA	阿拉伯语（沙特阿拉伯）
العربية (العراق)	ar-IQ	阿拉伯语（伊拉克）
العربية (الكويت)	ar-KW	阿拉伯语（科威特）
العربية (المغرب)	ar-MA	阿拉伯语（摩洛哥）
العربية (تونس)	ar-TN	阿拉伯语（突尼斯）
العربية (عُمان)	ar-OM	阿拉伯语（阿曼）
العربية (فلسطين)	ar-PS	阿拉伯语（巴勒斯坦国）
العربية (قطر)	ar-QA	阿拉伯语（卡塔尔）
العربية (لبنان)	ar-LB	阿拉伯语（黎巴嫩）
العربية (مصر)	ar-EG	阿拉伯语（埃及）
فارسی (ایران)	fa-IR	波斯语（伊朗）
हिन्दी (भारत)	hi-IN	印地语（印度）
ไทย (ประเทศไทย)	th-TH	泰语（泰国）
한국어 (대한민국)	ko-KR	韩语（韩国）
國語 (台灣)	cmn-Hant-TW	中文、普通话（台湾繁体）
廣東話 (香港)	yue-Hant-HK	中文、粤语（香港繁体）
日本語（日本）	ja-JP	日语（日本）
普通話 (香港)	cmn-Hans-HK	中文、普通话（香港简体）
普通话 (中国大陆)	cmn-Hans-CN	中文、普通话（中国简体）
```