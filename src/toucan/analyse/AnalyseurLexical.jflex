package toucan.analyse ;

import java_cup.runtime.*;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup
   
%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

%state commentaire

commentaireSlashSlash = [/][/].*
commentaireSlashEtoile = [/][*]
commentaireEtoileSlash = [*][/]

op = [*+-/]
tab = tab
idf = [A-Za-z_] [A-Za-z_0-9]*
typePrimitif = int|boolean|float|double|short|byte|long|char
cste = [0-9]+

%%

<YYINITIAL> {commentaireSlashSlash} {}

<YYINITIAL> {commentaireSlashEtoile} { yybegin(commentaire); }

<commentaire> {commentaireEtoileSlash} { yybegin(YYINITIAL); }

<YYINITIAL> ";"                	    { return symbol(CodesLexicaux.POINTVIRGULE); }

<YYINITIAL> "["                     { return symbol(CodesLexicaux.CROOUV); }

<YYINITIAL> "]"                     { return symbol(CodesLexicaux.CROFER); }

<YYINITIAL> "("                     { return symbol(CodesLexicaux.PAROUV); }

<YYINITIAL> ")"                     { return symbol(CodesLexicaux.PARFER); }

<YYINITIAL> "="                     { return symbol(CodesLexicaux.EGAL); }

<YYINITIAL> {op}                    { return symbol(CodesLexicaux.OP, yytext()); }

<YYINITIAL> {tab}                   { return symbol(CodesLexicaux.TAB, yytext()); }

<YYINITIAL> {typePrimitif}		    { return symbol(CodesLexicaux.TYPEPRIMITIF, yytext()); }

<YYINITIAL> {idf}			        { return symbol(CodesLexicaux.IDF, yytext()) ; }

<YYINITIAL> {cste}                  { return symbol(CodesLexicaux.CSTE, yytext()) ; }

.                       {}
\n                      {}
\r                      {}
