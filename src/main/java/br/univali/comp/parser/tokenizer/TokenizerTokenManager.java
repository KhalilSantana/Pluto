/* TokenizerTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. TokenizerTokenManager.java */
package br.univali.comp.parser.tokenizer;

/** Token Manager. */
public class TokenizerTokenManager implements TokenizerConstants {
        static int commentLevel = 0 ;

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 10:
         return jjStopAtPos(0, 2);
      case 32:
         return jjStopAtPos(0, 1);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 127;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 14)
                     kind = 14;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 6)
                        kind = 6;
                     { jjCheckNAddStates(0, 2); }
                  }
                  else if ((0xac2000000000L & l) != 0L)
                  {
                     if (kind > 9)
                        kind = 9;
                  }
                  else if ((0x530000000000L & l) != 0L)
                  {
                     if (kind > 13)
                        kind = 13;
                  }
                  else if ((0x5000004200000000L & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                  }
                  else if ((0x100002600L & l) != 0L)
                  {
                     if (kind > 3)
                        kind = 3;
                  }
                  else if (curChar == 58)
                     { jjAddStates(3, 5); }
                  else if (curChar == 61)
                     { jjCheckNAdd(56); }
                  else if (curChar == 39)
                     { jjCheckNAddTwoStates(7, 8); }
                  else if (curChar == 34)
                     { jjCheckNAddTwoStates(4, 5); }
                  if (curChar == 62)
                     { jjCheckNAdd(56); }
                  else if (curChar == 60)
                     { jjCheckNAdd(56); }
                  else if (curChar == 33)
                     { jjCheckNAdd(56); }
                  else if (curChar == 37)
                     jjstateSet[jjnewStateCnt++] = 12;
                  else if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 10;
                  else if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 3)
                     kind = 3;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 3:
                  if (curChar == 34)
                     { jjCheckNAddTwoStates(4, 5); }
                  break;
               case 4:
                  { jjCheckNAddTwoStates(4, 5); }
                  break;
               case 5:
                  if (curChar == 34 && kind > 8)
                     kind = 8;
                  break;
               case 6:
                  if (curChar == 39)
                     { jjCheckNAddTwoStates(7, 8); }
                  break;
               case 7:
                  { jjCheckNAddTwoStates(7, 8); }
                  break;
               case 8:
                  if (curChar == 39 && kind > 8)
                     kind = 8;
                  break;
               case 9:
                  if ((0xac2000000000L & l) != 0L && kind > 9)
                     kind = 9;
                  break;
               case 10:
                  if (curChar == 42 && kind > 9)
                     kind = 9;
                  break;
               case 11:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 12:
                  if (curChar == 37 && kind > 9)
                     kind = 9;
                  break;
               case 13:
                  if (curChar == 37)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 55:
                  if ((0x5000004200000000L & l) != 0L && kind > 12)
                     kind = 12;
                  break;
               case 56:
                  if (curChar == 61 && kind > 12)
                     kind = 12;
                  break;
               case 57:
                  if (curChar == 61)
                     { jjCheckNAdd(56); }
                  break;
               case 58:
                  if (curChar == 33)
                     { jjCheckNAdd(56); }
                  break;
               case 59:
                  if (curChar == 60)
                     { jjCheckNAdd(56); }
                  break;
               case 60:
                  if (curChar == 62)
                     { jjCheckNAdd(56); }
                  break;
               case 61:
                  if ((0x530000000000L & l) != 0L && kind > 13)
                     kind = 13;
                  break;
               case 62:
                  if (kind > 14)
                     kind = 14;
                  break;
               case 65:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjAddStates(6, 8); }
                  break;
               case 106:
                  if (curChar == 58)
                     { jjAddStates(3, 5); }
                  break;
               case 107:
                  if (curChar == 45)
                     { jjCheckNAddTwoStates(108, 109); }
                  break;
               case 108:
                  if ((0xfffffffffffffbffL & l) != 0L)
                     { jjCheckNAddTwoStates(108, 109); }
                  break;
               case 109:
                  if (curChar == 10 && kind > 4)
                     kind = 4;
                  break;
               case 110:
                  if (curChar == 42)
                     { jjCheckNAddTwoStates(111, 113); }
                  break;
               case 111:
                  { jjCheckNAddTwoStates(111, 113); }
                  break;
               case 112:
                  if (curChar == 58 && kind > 5)
                     kind = 5;
                  break;
               case 113:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 112;
                  break;
               case 114:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 115;
                  break;
               case 115:
                  if (kind > 15)
                     kind = 15;
                  { jjCheckNAdd(116); }
                  break;
               case 116:
                  if ((0xf80003ffffffffffL & l) == 0L)
                     break;
                  if (kind > 15)
                     kind = 15;
                  { jjCheckNAdd(116); }
                  break;
               case 117:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  { jjCheckNAddStates(0, 2); }
                  break;
               case 118:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 119;
                  break;
               case 119:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjstateSet[jjnewStateCnt++] = 120;
                  break;
               case 120:
                  if ((0x3ff000000000000L & l) != 0L && kind > 7)
                     kind = 7;
                  break;
               case 121:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddStates(9, 12); }
                  break;
               case 122:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAdd(118); }
                  break;
               case 123:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(122, 118); }
                  break;
               case 124:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddStates(13, 15); }
                  break;
               case 125:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjstateSet[jjnewStateCnt++] = 126;
                  break;
               case 126:
                  if ((0x3ff000000000000L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (kind > 14)
                     kind = 14;
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 11)
                        kind = 11;
                  }
                  else if ((0x2800000028000000L & l) != 0L)
                  {
                     if (kind > 13)
                        kind = 13;
                  }
                  else if (curChar == 124)
                  {
                     if (kind > 12)
                        kind = 12;
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     { jjCheckNAddStates(16, 21); }
                  else if (curChar == 95)
                     { jjCheckNAddStates(22, 25); }
                  if (curChar == 116)
                     { jjCheckNAddTwoStates(84, 105); }
                  else if (curChar == 101)
                     { jjAddStates(26, 27); }
                  else if (curChar == 105)
                     { jjAddStates(28, 29); }
                  else if (curChar == 110)
                     { jjAddStates(30, 31); }
                  else if (curChar == 100)
                     { jjCheckNAddTwoStates(83, 84); }
                  else if (curChar == 112)
                     { jjAddStates(32, 33); }
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 52;
                  else if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 45;
                  else if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 41;
                  else if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 37;
                  else if (curChar == 119)
                     jjstateSet[jjnewStateCnt++] = 33;
                  else if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 29;
                  else if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 25;
                  else if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 23;
                  else if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 4:
                  { jjAddStates(34, 35); }
                  break;
               case 7:
                  { jjAddStates(36, 37); }
                  break;
               case 14:
                  if (curChar == 101 && kind > 10)
                     kind = 10;
                  break;
               case 15:
               case 31:
                  if (curChar == 108)
                     { jjCheckNAdd(14); }
                  break;
               case 16:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 116 && kind > 10)
                     kind = 10;
                  break;
               case 23:
               case 25:
                  if (curChar == 101)
                     { jjCheckNAdd(22); }
                  break;
               case 24:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 26:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 25;
                  break;
               case 27:
                  if (curChar == 112 && kind > 10)
                     kind = 10;
                  break;
               case 28:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 30:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 32:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 33:
                  if (curChar == 104)
                     jjstateSet[jjnewStateCnt++] = 32;
                  break;
               case 34:
                  if (curChar == 119)
                     jjstateSet[jjnewStateCnt++] = 33;
                  break;
               case 35:
               case 101:
                  if (curChar == 115)
                     { jjCheckNAdd(14); }
                  break;
               case 36:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 35;
                  break;
               case 37:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if (curChar == 108 && kind > 10)
                     kind = 10;
                  break;
               case 40:
               case 87:
                  if (curChar == 97)
                     { jjCheckNAdd(39); }
                  break;
               case 41:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 40;
                  break;
               case 42:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 41;
                  break;
               case 43:
                  if (curChar == 114 && kind > 10)
                     kind = 10;
                  break;
               case 44:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 43;
                  break;
               case 45:
                  if (curChar == 104)
                     jjstateSet[jjnewStateCnt++] = 44;
                  break;
               case 46:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 45;
                  break;
               case 47:
                  if (curChar == 110 && kind > 10)
                     kind = 10;
                  break;
               case 48:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 47;
                  break;
               case 49:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 48;
                  break;
               case 50:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 49;
                  break;
               case 51:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 50;
                  break;
               case 52:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 51;
                  break;
               case 53:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 52;
                  break;
               case 54:
                  if ((0x7fffffe87fffffeL & l) != 0L && kind > 11)
                     kind = 11;
                  break;
               case 55:
                  if (curChar == 124 && kind > 12)
                     kind = 12;
                  break;
               case 61:
                  if ((0x2800000028000000L & l) != 0L && kind > 13)
                     kind = 13;
                  break;
               case 62:
                  if (kind > 14)
                     kind = 14;
                  break;
               case 63:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     { jjCheckNAddStates(16, 21); }
                  break;
               case 64:
                  if (curChar == 95)
                     { jjCheckNAdd(65); }
                  break;
               case 66:
                  if (curChar == 95)
                     { jjCheckNAddStates(22, 25); }
                  break;
               case 67:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     { jjCheckNAdd(65); }
                  break;
               case 68:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     { jjCheckNAddStates(6, 8); }
                  break;
               case 69:
                  if ((0x7fffffe07fffffeL & l) != 0L && kind > 11)
                     kind = 11;
                  break;
               case 70:
                  if (curChar == 95 && kind > 11)
                     kind = 11;
                  break;
               case 71:
                  if (curChar == 112)
                     { jjAddStates(32, 33); }
                  break;
               case 72:
                  if (curChar == 109 && kind > 10)
                     kind = 10;
                  break;
               case 73:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 72;
                  break;
               case 74:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 73;
                  break;
               case 75:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 74;
                  break;
               case 76:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 75;
                  break;
               case 77:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 76;
                  break;
               case 78:
                  if (curChar == 117)
                     { jjCheckNAdd(22); }
                  break;
               case 79:
                  if (curChar == 100)
                     { jjCheckNAddTwoStates(83, 84); }
                  break;
               case 80:
                  if (curChar == 110)
                     { jjCheckNAdd(14); }
                  break;
               case 81:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 80;
                  break;
               case 82:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 81;
                  break;
               case 83:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 82;
                  break;
               case 84:
                  if (curChar == 111 && kind > 10)
                     kind = 10;
                  break;
               case 85:
                  if (curChar == 110)
                     { jjAddStates(30, 31); }
                  break;
               case 86:
                  if (curChar == 111)
                     { jjCheckNAdd(22); }
                  break;
               case 88:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 87;
                  break;
               case 89:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 88;
                  break;
               case 90:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 89;
                  break;
               case 91:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 90;
                  break;
               case 92:
                  if (curChar == 105)
                     { jjAddStates(28, 29); }
                  break;
               case 93:
                  if (curChar == 115 && kind > 10)
                     kind = 10;
                  break;
               case 94:
                  if (curChar == 102 && kind > 10)
                     kind = 10;
                  break;
               case 95:
                  if (curChar == 101)
                     { jjAddStates(26, 27); }
                  break;
               case 96:
                  if (curChar == 116)
                     { jjCheckNAdd(14); }
                  break;
               case 97:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 96;
                  break;
               case 98:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 97;
                  break;
               case 99:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 98;
                  break;
               case 100:
                  if (curChar == 120)
                     jjstateSet[jjnewStateCnt++] = 99;
                  break;
               case 102:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 101;
                  break;
               case 103:
                  if (curChar == 116)
                     { jjCheckNAddTwoStates(84, 105); }
                  break;
               case 104:
                  if (curChar == 117)
                     { jjCheckNAdd(14); }
                  break;
               case 105:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 104;
                  break;
               case 108:
                  { jjAddStates(38, 39); }
                  break;
               case 111:
                  { jjAddStates(40, 41); }
                  break;
               case 115:
               case 116:
                  if (kind > 15)
                     kind = 15;
                  { jjCheckNAdd(116); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2) && kind > 14)
                     kind = 14;
                  break;
               case 4:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(34, 35); }
                  break;
               case 7:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(36, 37); }
                  break;
               case 108:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(38, 39); }
                  break;
               case 111:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(40, 41); }
                  break;
               case 115:
               case 116:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 15)
                     kind = 15;
                  { jjCheckNAdd(116); }
                  break;
               default : if (i1 == 0 || l1 == 0 || i2 == 0 ||  l2 == 0) break; else break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 127 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   118, 121, 125, 107, 110, 114, 54, 63, 66, 122, 118, 123, 124, 122, 118, 123, 
   64, 65, 54, 70, 63, 66, 67, 65, 68, 69, 100, 102, 93, 94, 86, 91, 
   77, 78, 4, 5, 7, 8, 108, 109, 111, 113, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         return false;
   }
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public TokenizerTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public TokenizerTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  
  public void ReInit(SimpleCharStream stream)
  {


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 127; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0xffc1L, 
};
static final long[] jjtoSkip = {
   0x3eL, 
};
static final long[] jjtoSpecial = {
   0x0L, 
};
static final long[] jjtoMore = {
   0x0L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[127];
    private final int[] jjstateSet = new int[2 * 127];
    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;
}
