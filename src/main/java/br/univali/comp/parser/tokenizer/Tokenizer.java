/* Tokenizer.java */
/* Generated By:JavaCC: Do not edit this line. Tokenizer.java */
package br.univali.comp.parser.tokenizer;


public class Tokenizer implements TokenizerConstants {
    private StringBuilder results = new StringBuilder();

    public void handleToken()  {
            try {
                Token t = null;
                t = getNextToken();
                if(t.kind == EOF) return;
                switch(t.kind){
                    case INTEGER_CONSTANT:{
                        results.append("Token: '"+t.image+"' - Type: INTEGER_CONSTANT-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    case FLOAT_CONSTANT:{
                        results.append("Token: '"+t.image+"' - Type: FLOAT_CONSTANT-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    case LITERAL_CONSTANT:{
                        results.append("Token: '"+t.image+"' - Type: LITERAL_CONSTANT-"+t.kind+" - Line-Column-begin: "+t.beginLine+"-"+t.beginColumn+ " ; Line-Column-end: "+t.endLine+"-"+t.endColumn+"\n");
                        break;
                    }
                    // Removido de acordo com correção prof.
//                    case ARITHMETIC:{
//                        results.append("Token: '"+t.image+"' - Type: ARITHMETIC-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
//                        break;
//                    }
                    case IDENTIFIER:{
                        results.append("Token: '"+t.image+"' - Type: IDENTIFIER-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    // Removido de acordo com correção prof.
//                    case LOGICAL:{
//                        results.append("Token: '"+t.image+"' - Type: LOGICAL-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
//                        break;
//                    }
                    case SPECIAL_SYMBOL:{
                        results.append("Token: '"+t.image+"' - Type: SPECIAL_SYMBOL-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    case RESERVED_WORDS:{
                        results.append("Token: '"+t.image+"' - Type: RESERVED_WORDS-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
//                    TO generic errors
//                    case ERROR:{
//                        results.append("ERROR: '"+t.image+"' - Type: Invalid Token-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
//                        break;
//                    }
                    case ERROR_INVALID_SYMBOL:{
                        results.append("ERROR: '"+t.image+"' - Type: ERROR_INVALID_SYMBOL-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    case ERROR_UNFINISHED_LITERAL_CONSTANT:{
                        results.append("ERROR_UNFINISHED_LITERAL_CONSTANT: \n'"+t.image+"' \n Type: ERROR_UNFINISHED_LITERAL_CONSTANT-"+t.kind+" - Line-Column-begin: "+t.beginLine+"-"+t.beginColumn+ " ; Line-Column-end: "+t.endLine+"-"+t.endColumn+"\n");
                        break;
                    }
                    case ERROR_INTEGER_CONSTANT_FORMAT:{
                        results.append("ERROR: '"+t.image+"' - Type: ERROR_INTEGER_CONSTANT_FORMAT-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    case ERROR_FLOAT_CONSTANT_FORMAT:{
                        results.append("ERROR: '"+t.image+"' - Type: ERROR_FLOAT_CONSTANT_FORMAT-"+t.kind+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                    default:{
                        results.append("<DEFAULT UNFOUND: Image:"+t.image+ " | Kind:" +t.kind +">"+" - Line-Column: "+t.beginLine+"-"+t.beginColumn+"\n");
                        break;
                    }
                }
                handleToken();
            }catch(Error error){
                results.append("Error - " + error.getMessage() + "\n");
                System.out.println(error.toString());
                handleToken();
            }
        }

    public String getTokens(String args[], String textToAnalyze) {
            Tokenizer tokenizer;
            if(args.length == 0){
                System.out.println("Reading from received text!");
                java.io.InputStream targetStream = new java.io.ByteArrayInputStream(textToAnalyze.getBytes());
                tokenizer = new Tokenizer(targetStream);
            }
                else if(args.length == 1){
                        try{
                    tokenizer = new Tokenizer(new java.io.FileInputStream(args[0]));
                        }
                        catch(java.io.FileNotFoundException e){
                                System.err.println(args[0] + " was not found." );
                                System.err.println(e);
                                return args[0] + " was not found.";
                        }
                }
                else{
                        System.out.println("Use:\njava Tokenizer < inputFile");
                        System.out.println("or java Tokenizer inputFile");
                        return "Use:java Tokenizer < inputFile";
                }

                this.handleToken();

                results.append("<EOF>");
                return results.toString();
        }


    public static void main(String args[])  throws ParseException {
        Tokenizer parser = new Tokenizer( System.in ) ;
        parser.getTokens(args, "");
        System.out.println(parser.results);
    }

    public String getResults(){
            return results.toString();
    }

  /** Generated Token Manager. */
  public TokenizerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {};
	}

  /** Constructor with InputStream. */
  public Tokenizer(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Tokenizer(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new TokenizerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Tokenizer(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new TokenizerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new TokenizerTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
  }

  /** Constructor with generated Token Manager. */
  public Tokenizer(TokenizerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
  }

  /** Reinitialise. */
  public void ReInit(TokenizerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[19];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 0; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 19; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
