package lexical_analysis;

import java.util.LinkedList;

public class Formal_language {
	
	public static void main(String args[]){
		LinkedList<Token> linkedList = new LinkedList<Token>();
		System.out.println("Start lexical_analysis...");
		String CODE ="int variable = 10;"
				+ "float myNUM , anotherNUM;"
				+ "for(int i = 0; i < variable; i++){"
				+ "	 if(myNUM+myNUM == anotherNUM* myNUM){}" 
				+ "  else {myNUM += anotherNUM* myNUM}"
				+ "}";
			
		linkedList = lexical_analysis(CODE);
		System.out.println("Finised lexical_analysis, the output is: \n");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println("<"+linkedList.get(i).getType()+", '"+linkedList.get(i).getValue()+"'>");
		}
	}
	
	/**
	 * INPUT: SEQENCE OF PROGRAMMING LANGUAGE CODE LETTERS
	 * OUTPUT: CHECK IF THE INPUT MATCHES THE LEXICAL ANALYSIS TEST, IF SO 
	 * RETURN AN ARRAY OF TOKENS.
	 * i.e: 
	 * INPUT: String input = "for(int i = 0; i < 10; i++)"
	 * OUTPUT: <keyword, 'T_for'>, <extra, '('> ,<keyword, T_int> ,<identifier, i>
	 * 		   <operation, '='>, <number, '0'>, <extra, ';'> ,<identifier, 'i'> ,
	 * 		   <operation, '<'>, <number, '10'>, <extra, ';'>, <identifier, 'i'>,
	 * 		   <extra, '++'>, <extra, ')'>
	 * */
	 
	public static LinkedList<Token> lexical_analysis(String input){
		input = input.trim();
		int startOfWord = 0, counter = 0;
		char current_char, next_char  ;
		LinkedList<Token> linkedList = new LinkedList<Token>();
	    Token token;
	    String substring;
		/**
		 * The input here must be formal
		 * i.e all the several white spaces is compacted to one white space.
		 */
	    int size = input.length();
	    
		for(int i = 0; i < size; i++){
			current_char = input.charAt(i);
			if(i < size-1) next_char = input.charAt(i+1);
			else next_char = 0;
			
			if(is_extra(current_char, next_char) == null && !current_str.equals(" ")
					&&is_operation(current_char) == null){
				counter++;
			} else {
				
				substring = input.substring(startOfWord, startOfWord+counter);
				if((token = checker(substring)) != null) linkedList.add(token);
				if(is_operation(current_char) != null && is_extra(current_char, next_char) == null) {
					linkedList.add(new Token("operation", String.valueOf(input.charAt(startOfWord+counter))));
//					System.out.println("operation"+input.charAt(startOfWord+counter));
				}
				/**
				 * ADD THE SPECIAL TOKEN.
				 */
//				System.out.println("current_char= "+current_char+"  next_char= "+next_char);
				if((value = is_extra(current_char, next_char)) != null) {
					 linkedList.add(new Token("special", value));
					
					if(value.equals("==") || value.equals(">=") || value.equals("<=")
					   || value.equals("++") || value.equals("--") || value.equals("+=")
					   || value.equals("-=") || value.equals("*=") || value.equals("%=")) i++;
				} 
				counter = 0;
				startOfWord = i+1;
			}
		}
		 
		return linkedList;
	}
	
	/**
	 * INPUT STRING
	 * OUTPUT TOKEN NODE
	 * i.e: 
	 * INPUT: for
	 * OUTPUT: <keyword, 'for_T'>
	 */
	static String value, value2;
	private static Token checker(String token){
		/**
		 * KEYWORDS
		 */
//		System.out.println("token= "+token);
		if(is_keyword(token) != null) return new Token("keyword", token.concat("_T"));
		/**
		 * IDENTIFIER
		 */
		if(is_identifier(token) != null) return new Token("identifier", token);
		if(isNumeric(token)) return new Token("number	", token);
		if(token.length()==1
				&& (value2 = is_operation(token.charAt(0))) != null)return new Token("operation", value2);

		return null;
	}
	
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	/**
	 * identifier is a sequence of a letter followed by a (letter or a digit)*  
	 */
	static int sizeOFIdentifeir;
	private static String is_identifier(String s){
		sizeOFIdentifeir = s.length();
		if(sizeOFIdentifeir != 0){
			if(Character.isLetter(s.charAt(0))) {
				for (int i = 1; i < sizeOFIdentifeir; i++) {
					if(!Character.isLetter(s.charAt(i)) 
							&& !Character.isDigit(s.charAt(i)))
						return null;
				}
			} else return null;
		} else return null;
		
		return s;
	}
	
	
	private static String is_keyword(String s){
		switch( s ){
			case "abstract": return "abstract";
			case "continue": return "continue";
			case "for": return "for";
			case "new": return "new";
			case "switch": return "switch";
			case "assert": return "assert";
			case "default": return "default";
			case "package": return "package";
			case "synchronized": return "synchronized";
			case "boolean": return "boolean";
			case "do": return "do";
			case "if": return "if";
			case "private": return "private";
			case "this": return "this";
			case "break": return "break";
			case "double": return "double";
			case "implements": return "implements";
			case "protected": return "protected";
			case "throw": return "throw";
			case "byte": return "byte";
			case "else": return "else";
			case "import": return "import";
			case "public": return "public";
			case "throws": return "throws";
			case "case": return "case";
			case "enum": return "enum";
			case "instanceof": return "instanceof";
			case "return": return "return";
			case "transient": return "transient";
			case "catch": return "catch";
			case "extends": return "extends";
			case "int": return "int";
			case "short": return "short";
			case "try": return "try";
			case "char": return "char";
			case "final": return "final";
			case "interface": return "interface";
			case "static": return "static";
			case "void": return "void";
			case "class": return "class";
			case "finally": return "finally";
			case "long": return "long";
			case "strictfp": return "strictfp";
			case "volatile": return "volatile";
			case "const": return "const";
			case "float": return "float";
			case "native": return "native";
			case "super": return "super";
			case "while": return "while";
			
			default: return null;
		}
			
	}
	
	private static String is_operation(char current_char){
		current_str = String.valueOf(current_char);
		
		if(current_str.equals("=")) return "=";
		if(current_str.equals("+")) return "+";
		if(current_str.equals("-")) return "-";
		if(current_str.equals("*")) return "*";
		if(current_str.equals("/")) return "/";
		if(current_str.equals("%")) return "%";
		if(current_str.equals("&")) return "&";
		if(current_str.equals("|")) return "|";
		if(current_str.equals("!")) return "!";
		
		return null;
	}
	
	static String current_str;
	static String next_str;
	private static String is_extra(char current_char, char next_char){ 
		current_str = String.valueOf(current_char);
		if(next_char != 0) next_str = String.valueOf(next_char);
		else next_str = "";
		
		if(current_str.equals("<") 
				&& next_str.equals("=")) return "<=";
		if(current_str.equals(">") 
				&& next_str.equals("=")) return ">=";
		if(current_str.equals("=") 
				&& next_str.equals("=")) return "==";
		if(current_str.equals("!") 
				&& next_str.equals("=")) return "!=";
		if(current_str.equals("+") 
				&& next_str.equals("+")) return "++";
		if(current_str.equals("-") 
				&& next_str.equals("-")) return "--";
		if(current_str.equals("+") 
				&& next_str.equals("=")) return "+=";
		if(current_str.equals("-") 
				&& next_str.equals("=")) return "-=";
		if(current_str.equals("*") 
				&& next_str.equals("=")) return "*=";
		if(current_str.equals("/") 
				&& next_str.equals("=")) return "/=";
		if(current_str.equals("%") 
				&& next_str.equals("=")) return "%=";
		
		if(current_str.equals("<")) return "<";
		if(current_str.equals(">")) return ">";
//		if(current_str.equals("=")) return "=";
		
		if(current_str.equals(";")) return ";";
		if(current_str.equals(",")) return ",";
		if(current_str.equals(")")) return ")";
		if(current_str.equals("(")) return "(";
		if(current_str.equals("{")) return "{";
		if(current_str.equals("}")) return "}";
		
		return null;
	}
}