# Trumpet
 Trumpet is a simple audio player written in Java using JavaFX framework. It can play MP3, WAV and MP4 (audio only) files. It has basic features such as playback control, progress slider, volume & speed control and playlist

## Download Trumpet
You can download prebuilt binaries of Trumpet from [trumpet page on my website](https://tekronet.github.io/programy/trumpet.html)

## Compiling from source
If you are MacOS user or you don't wanna use prebuilt binaries you can comiple trumpet from source. Here is how to do it.

1. Download latest Java Development Kit, JavaFX sdk and Apache ant build tool.
2. Download Trumpet source code from this repo
3. Create "lib" folder in project's root and put files from javafx sdk here.
4. Compile program by opening terminal/cmd in project's root and runing 'ant run' on GNU/Linux or MacOS or 'ant windows_run' for Windows.
5. After that program should start. Executable jar file can be found in project's root/jar directory. NOTE: This jar file depends on these modules: java.base,javafx.base,javafx.graphics,javafx.controls,javafx.media so when you try to run it you should provide a module path with those.
