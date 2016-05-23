import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TestMiniC {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		MiniCLexer lexer = new MiniCLexer( new ANTLRFileStream("test.c")); 
	 	CommonTokenStream tokens = new CommonTokenStream( lexer ); 
	 	MiniCParser parser  	= new MiniCParser( tokens ); 
	  	ParseTree  	tree   	= parser.program(); 
	  	
	  	//여기부터 새로운 부분
	  	ParseTreeWalker walker = new ParseTreeWalker();
	  	walker.walk(new MiniCPrintListener(), tree);
	}
}
