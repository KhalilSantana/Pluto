/* Generated By:JavaCC: Do not edit this line. TokenizerConstants.java */
package br.univali.comp.parser.tokenizer;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface TokenizerConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SPACE = 1;
  /** RegularExpression Id. */
  int EOL = 2;
  /** RegularExpression Id. */
  int SKIP_MARKERS = 3;
  /** RegularExpression Id. */
  int COMMENT_LINE = 4;
  /** RegularExpression Id. */
  int COMMENT_BLOCK = 5;
  /** RegularExpression Id. */
  int INTEGER_CONSTANT = 6;
  /** RegularExpression Id. */
  int FLOAT_CONSTANT = 7;
  /** RegularExpression Id. */
  int LITERAL_CONSTANT = 8;
  /** RegularExpression Id. */
  int ARITHMETIC = 9;
  /** RegularExpression Id. */
  int RESERVED_WORDS = 10;
  /** RegularExpression Id. */
  int IDENTIFIER = 11;
  /** RegularExpression Id. */
  int LOGICAL = 12;
  /** RegularExpression Id. */
  int SPECIAL_SYMBOL = 13;
  /** RegularExpression Id. */
  int ERROR_INVALID_SYMBOL = 14;
  /** RegularExpression Id. */
  int ERROR_UNFINISHED_BLOCK_COMMENT = 15;
  /** RegularExpression Id. */
  int ERROR_UNFINISHED_LITERAL_CONSTANT = 16;
  /** RegularExpression Id. */
  int ERROR_INTEGER_CONSTANT_FORMAT = 17;
  /** RegularExpression Id. */
  int ERROR_FLOAT_CONSTANT_FORMAT = 18;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\n\"",
    "<SKIP_MARKERS>",
    "<COMMENT_LINE>",
    "<COMMENT_BLOCK>",
    "<INTEGER_CONSTANT>",
    "<FLOAT_CONSTANT>",
    "<LITERAL_CONSTANT>",
    "<ARITHMETIC>",
    "<RESERVED_WORDS>",
    "<IDENTIFIER>",
    "<LOGICAL>",
    "<SPECIAL_SYMBOL>",
    "<ERROR_INVALID_SYMBOL>",
    "<ERROR_UNFINISHED_BLOCK_COMMENT>",
    "<ERROR_UNFINISHED_LITERAL_CONSTANT>",
    "<ERROR_INTEGER_CONSTANT_FORMAT>",
    "<ERROR_FLOAT_CONSTANT_FORMAT>",
  };

}
