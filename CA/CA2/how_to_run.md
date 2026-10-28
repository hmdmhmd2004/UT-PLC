java -jar .\utilities\antlr-4.13.1-complete.jar -package main.grammar -visitor .\src\main\grammar\SimpleLang.g4

Get-ChildItem -Path .\src -Recurse -Filter *.java | Where-Object { $_.FullName -notmatch '\\\.antlr\\' } | Select-Object -ExpandProperty FullName | Out-File -Encoding ascii sources.txt

javac -cp ".\src;.\utilities\antlr-4.13.1-complete.jar" "@sources.txt"

java -cp ".\src;.\utilities\antlr-4.13.1-complete.jar" SimpleLang .\samples\sample1.mol > my_output.txt

Get-Content my_output.txt