import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.omg.Messaging.SyncScopeHelper;

public class MiniCPrintListener extends MiniCBaseListener {

	ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
	int blink_cnt = 0;
	

	boolean isBinaryOperation(MiniCParser.ExprContext ctx) {
		return ctx.getChildCount() == 3 
				&& ctx.getChild(1) != ctx.expr(0);
		// ‘(‘ expr ’)’를 배제
	}
	// 프로그램이 종료될때 출력.
	@Override public void exitProgram(MiniCParser.ProgramContext ctx) 
	{ 
		String decl = newTexts.get(ctx.decl(0));
		int decl_cnt = ctx.decl().size();
		for(int i=1; i<decl_cnt; i++)
			decl +="\n"+ newTexts.get(ctx.decl(i));
		System.out.println();
		System.out.println(decl);
	}
	@Override public void exitDecl(MiniCParser.DeclContext ctx) 
	{ 
		String var_decl = null, fun_decl = null;
		if(ctx.getChild(0).equals(ctx.var_decl()))
		{	
			var_decl = newTexts.get(ctx.var_decl());
			newTexts.put(ctx, var_decl);
		}
		else
		{
			fun_decl = newTexts.get(ctx.fun_decl());
			newTexts.put(ctx, fun_decl);
		}
	}
	@Override public void exitVar_decl(MiniCParser.Var_declContext ctx) 
	{ 
		String type_spec = null, ident = null;
		type_spec = newTexts.get(ctx.type_spec());
		ident = newTexts.get(ctx.IDENT());
		if(ctx.getChildCount() == 3){
			newTexts.put(ctx, type_spec+ident+";");
		}
		else {
			newTexts.put(ctx, type_spec+ident+"[]"+";");
		}
	}
	@Override public void exitType_spec(MiniCParser.Type_specContext ctx) 
	{ 
		if(ctx.getChild(0) == ctx.VOID())
			newTexts.put(ctx, ctx.VOID().getText());
		else
			newTexts.put(ctx, ctx.INT().getText());
	}
	//함수일때
	@Override public void exitFun_decl(MiniCParser.Fun_declContext ctx) 
	{ 
		String type_spec = null, ident = null, params =null, compound_stmt = null;			
		type_spec = newTexts.get(ctx.type_spec());
		ident =ctx.IDENT().getText();
		params = newTexts.get(ctx.params());
		compound_stmt = newTexts.get(ctx.compound_stmt());
		newTexts.put(ctx, type_spec+" "+ident+"("+params+")\n"+compound_stmt);
	}
	@Override public void exitParams(MiniCParser.ParamsContext ctx) 
	{
		String param = newTexts.get(ctx.param(0));
		int param_cnt = ctx.param().size();
		for(int i = 1; i<param_cnt ; i++)
			param += ","+newTexts.get(ctx.param(i));
		if (ctx.getChildCount() == 0)
			newTexts.put(ctx, "");
		else if(ctx.getChild(0).getText().equals("void")){
			newTexts.put(ctx, "void");
		}
		else { 
			newTexts.put(ctx, param);
		}
	}	
	@Override public void exitParam(MiniCParser.ParamContext ctx) 
	{ 
		String type_spec = null, ident = null;
		type_spec = newTexts.get(ctx.type_spec());
		ident = ctx.IDENT().getText();
		if(ctx.getChildCount() == 2){
			newTexts.put(ctx, type_spec+" "+ident);
		}
		else {
			newTexts.put(ctx, type_spec+" "+ident+"[]");
		}
	}
	
	@Override public void exitStmt(MiniCParser.StmtContext ctx) 
	{ 
		String expr_stmt = null,compound_stmt = null, if_stmt= null,while_stmt= null, return_stmt= null;
		if(ctx.getChild(0).equals(ctx.expr_stmt())){
			expr_stmt = newTexts.get(ctx.expr_stmt());
			newTexts.put(ctx, expr_stmt);
		}
		else if(ctx.getChild(0).equals(ctx.compound_stmt()) ){
			compound_stmt = newTexts.get(ctx.compound_stmt());
			newTexts.put(ctx, compound_stmt);
		}
		else if(ctx.getChild(0).equals(ctx.if_stmt()) ){
			if_stmt = newTexts.get(ctx.if_stmt());
			newTexts.put(ctx, if_stmt);
		}
		else if(ctx.getChild(0).equals(ctx.while_stmt()) ){
			while_stmt = newTexts.get(ctx.while_stmt());
			newTexts.put(ctx, while_stmt);
		}
		else if(ctx.getChild(0).equals(ctx.return_stmt())){
			return_stmt = newTexts.get(ctx.return_stmt());
			newTexts.put(ctx, return_stmt);
		}
	}
	@Override public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) 
	{ 
		String expr = null;
		expr = newTexts.get(ctx.expr());
		newTexts.put(ctx, expr+";\n");
	}
	@Override public void enterWhile_stmt(MiniCParser.While_stmtContext ctx) 
	{
		if (!(ctx.stmt().getChild(0) instanceof MiniCParser.Compound_stmtContext))
			blink_cnt++;
	}
	@Override public void exitWhile_stmt(MiniCParser.While_stmtContext ctx)
	{ 
		String _while = null, expr = null, stmt = null;
		String blink = "";
		for(int i =1; i<blink_cnt;i++)
			blink +="    ";
		_while = ctx.WHILE().getText();
		expr = newTexts.get(ctx.expr());
		stmt = newTexts.get(ctx.stmt());
		
		if (!(ctx.stmt().getChild(0) instanceof MiniCParser.Compound_stmtContext)) 
		{
			newTexts.put(ctx, _while+ " ("+expr+")\n    "+blink+stmt);
			blink_cnt--;
		}
		else{
			newTexts.put(ctx, _while+"("+expr+")\n"+stmt);
		}
	}
	@Override public void enterCompound_stmt(MiniCParser.Compound_stmtContext ctx) 
	{
		blink_cnt++;
	}
	
	@Override public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) 
	{ 

		String local_decl = newTexts.get(ctx.local_decl(0)), stmt = newTexts.get(ctx.stmt(0));
		String blink = "";
		
		for(int i =1; i<blink_cnt;i++)
			blink +="    ";
		int local_cnt = ctx.local_decl().size(),stmt_cnt = ctx.stmt().size();
		
		for(int i = 1; i<local_cnt ; i++)
			local_decl =local_decl+blink+"    "+ newTexts.get(ctx.local_decl(i));
		for(int i = 1; i<stmt_cnt ; i++){
			stmt =stmt+blink+"    "+newTexts.get(ctx.stmt(i));
		}
		if(local_cnt == 0 && stmt_cnt == 0){
			newTexts.put(ctx, blink+"{\n"+blink+"}\n");
		}
		else if(local_cnt !=0 && stmt_cnt == 0){
			newTexts.put(ctx, blink+"{\n"+blink+"    "+local_decl+blink+"}\n");
		}
		else if(local_cnt ==0 && stmt_cnt != 0){
			newTexts.put(ctx, blink+"{\n"+blink+"    "+stmt+blink+"}\n");
		}
		else {
			newTexts.put(ctx, blink+"{\n"+blink+"    "+local_decl+blink+"    "+stmt+blink+"}\n");
		}
		blink_cnt--;
	}
	@Override public void exitLocal_decl(MiniCParser.Local_declContext ctx) 
	{ 
		String type_spec = null, ident = null;
		type_spec = newTexts.get(ctx.type_spec());
		ident = ctx.IDENT().getText();
		if(ctx.getChildCount() == 3){
			newTexts.put(ctx, type_spec+" "+ident+";\n");
		}
		else {
			newTexts.put(ctx, type_spec+" "+ident+"[]"+";\n");
		}
	}
	@Override public void enterIf_stmt(MiniCParser.If_stmtContext ctx) 
	{
		if (!(ctx.stmt(0).getChild(0) instanceof MiniCParser.Compound_stmtContext))
			blink_cnt++;
	}
	@Override public void exitIf_stmt(MiniCParser.If_stmtContext ctx) 
	{ 
		
		String s1 = null, s2 = null, s3 = null, s4 = null, s5 = null;
		String blink = "";
		for(int i =1; i<blink_cnt;i++)
			blink +="    ";
		if(ctx.getChildCount() == 5){
			s1 = ctx.IF().getText();
			s2 = newTexts.get(ctx.expr());
			s3 = newTexts.get(ctx.stmt(0));
			if (!(ctx.stmt(0).getChild(0) instanceof MiniCParser.Compound_stmtContext)) 
			{
				newTexts.put(ctx, s1+ " ("+s2+")\n    "+blink+s3);
				blink_cnt--;
			}
			else
			{
				newTexts.put(ctx, s1+ " ("+s2+")\n"+s3);
			}
		}
		else if (ctx.getChildCount() == 7 && ctx.getChild(5) == ctx.ELSE()){
			s1 = newTexts.get(ctx.IF());
			s2 = newTexts.get(ctx.expr());
			s3 = newTexts.get(ctx.stmt(0));
			s4 = newTexts.get(ctx.ELSE());
			s5 = newTexts.get(ctx.stmt(1));
			newTexts.put(ctx, s1+ " ("+s2+")\n"+s3+"\n"+s4+"\n"+s5);
		}
	}
	@Override public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) 
	{ 
		String _return = null,expr=null;
		_return  = ctx.RETURN().getText();
		if(ctx.getChildCount() == 2){
			newTexts.put(ctx, _return+";\n");
		}
		else{
			expr = newTexts.get(ctx.expr());
			newTexts.put(ctx, _return+" "+expr+";\n");
		}
	}
	@Override public void exitExpr(MiniCParser.ExprContext ctx) { 
		String expr1 = null, expr2 = null, op = null;
		String ident  = null,args = null,literal;
		
		// 이진 연산일때
		if(isBinaryOperation(ctx))
		{
			// 예: expr ‘+’ expr
			if(ctx.getChild(0) == ctx.IDENT()){
				ident = ctx.IDENT().getText();
				expr1 = newTexts.get(ctx.expr(0));
				newTexts.put(ctx, ident+" = "+expr1);
			}else {
				expr1 = newTexts.get(ctx.expr(0));
				expr2 = newTexts.get(ctx.expr(1));
				op = ctx.getChild(1).getText();
				newTexts.put(ctx, expr1 +" "+ op +" "+ expr2);
			}
		}
		else if(ctx.getChildCount() == 3 ){
			expr1 = newTexts.get(ctx.expr(0));
			newTexts.put(ctx, "(" + expr1 + ")");
		}
		else if (ctx.getChildCount() == 4 && ctx.getChild(2) == ctx.expr()){
			ident = ctx.IDENT().getText();
			expr1 = newTexts.get(ctx.expr(0));
			newTexts.put(ctx, ident+"[" + expr1 + "]");
		}
		else if (ctx.getChildCount() == 4 && ctx.getChild(2) == ctx.args()){
			ident = ctx.IDENT().getText();
			args = newTexts.get(ctx.args());
			newTexts.put(ctx, ident+"(" + args + ")");
		}
		else if(ctx.getChildCount() == 2) {
			expr1 = newTexts.get(ctx.expr(0));
			if(ctx.getChild(0).equals("!")){
				newTexts.put(ctx, "!"+expr1);
			}else if(ctx.getChild(0).equals("-")){
				newTexts.put(ctx, "-"+expr1);
			}else {
				newTexts.put(ctx, "+"+expr1);
			}
		}
		else if(ctx.getChildCount() == 6){
			ident = ctx.IDENT().getText();
			expr1 = newTexts.get(ctx.expr(0));
			expr2 = newTexts.get(ctx.expr(1));
			
			newTexts.put(ctx, ident+"["+expr1+"]"+" = "+expr2);
		}
		else {
			newTexts.put(ctx, ctx.getChild(0).getText());
		}
	}
	@Override public void exitArgs(MiniCParser.ArgsContext ctx) 
	{
		String expr = newTexts.get(ctx.expr(0));
		int expr_cnt = ctx.expr().size();
		for(int i=1; i<expr_cnt ; i++)
			expr +=","+newTexts.get(ctx.expr(i));
		newTexts.put(ctx, expr);
	}	
}
