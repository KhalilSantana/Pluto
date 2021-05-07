ECHO ========== Cria os arquivos do analisador com o javaCC
javacc parser.jj && ^
ECHO ========== Compila a classe do analisador && ^
javac *.java
::ECHO ========== Executa o teste com o arquivo de testes criado. && ^
::java Tokenizer.java teste.txt & ^
::ECHO ========== Teste Finalizado
PAUSE