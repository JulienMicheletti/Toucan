cd toucan
cd analyse
java -jar C:/java-cup-11a.jar -parser  AnalyseurSyntaxique -symbols CodesLexicaux Grammaire.cup
java -jar C:/jflex-1.6.1.jar AnalyseurLexical.jflex
cd ../..
javac -cp C:/java-cup-11a.jar toucan/analyse/*.java
java -cp C:/java-cup-11a.jar;. toucan.analyse.TestAnalyse fichier
pause

