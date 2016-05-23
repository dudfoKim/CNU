import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class UcodeGenListener extends MiniCBaseListener {

	ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
	Map<String, String> map = new HashMap<String, String>();
	ArrayList<Integer> pre_local= new ArrayList<Integer>();

	String blink_11 = "           ";
	String blink_3 = "   ";
	String blink_2 = "  ";
	int lexical_level = 0;
	int block_level = 0;
	int global_cnt = 0;
	int local_var_cnt = 0;
	int pre_local_var_cnt = 0;
	int param_cnt = 0;
	int index = 0;
	
	boolean isBinaryOperation(MiniCParser.ExprContext ctx) {
		return ctx.getChildCount() == 3 
				&& ctx.getChild(1) != ctx.expr(0);
		// ‘(‘ expr ’)’를 배제
	}
	// 프로그램이 종료될때 출력.
	@Override public void enterProgram(MiniCParser.ProgramContext ctx){
		block_level++;
		System.out.println();
	}
	@Override public void exitProgram(MiniCParser.ProgramContext ctx) 
	{ 
		String mString = "";
		lexical_level = 1;
		mString += blink_11+"bgn"+blink_3+0+"\n";
		String decl = newTexts.get(ctx.decl(0));
		
		int decl_cnt = ctx.decl().size();
		for(int i=1; i<decl_cnt; i++){
				decl +="\n"+ newTexts.get(ctx.decl(i));
		}
		if(global_cnt > 0)
			mString += blink_11+"proc"+blink_2+global_cnt+blink_2+block_level+blink_2+lexical_level+"\n";
		mString += decl;
		mString += "\n"+blink_11+"end"+"\n";
		System.out.print(mString);
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
	@Override public void enterVar_decl(MiniCParser.Var_declContext ctx)
	{
		String ident = null;
		ident = ctx.IDENT().getText();
		if(ctx.getChildCount() == 3){
			global_cnt++;
			String map_val = block_level+blink_2+global_cnt;
			map.put(ident, map_val);
		} else{
			int size = Integer.parseInt(ctx.LITERAL().getText());
			int start ;
			global_cnt += size;
			start = global_cnt - size+1;
			String map_val = block_level+blink_2+start;
			map.put(ident, map_val);
		}
	}
	@Override public void exitVar_decl(MiniCParser.Var_declContext ctx) 
	{ 
	
		if(ctx.getChildCount() == 3){
			newTexts.put(ctx, blink_11+"sym"+blink_3+block_level+blink_2+global_cnt+blink_2+"1");
		}
		else {
			int size = Integer.parseInt(ctx.LITERAL().getText());
			int start ;
			start = global_cnt - size+1;
			newTexts.put(ctx, blink_11+"sym"+blink_3+block_level+blink_2+start+blink_2+size);
		}
	}
	// 이건 안쓰는것 같아서 이전 문법에서 그대로 남겨놨습니다.
	@Override public void exitType_spec(MiniCParser.Type_specContext ctx) 
	{ 
		if(ctx.getChild(0) == ctx.VOID())
			newTexts.put(ctx, ctx.VOID().getText());
		else
			newTexts.put(ctx, ctx.INT().getText());
	}
	@Override public void enterFun_decl(MiniCParser.Fun_declContext ctx) {
		block_level++;
		local_var_cnt =0;
		pre_local_var_cnt = 0;
		param_cnt=0;
	}
	@Override public void exitFun_decl(MiniCParser.Fun_declContext ctx) 
	{ 
		String ident = null, params ="", compound_stmt = null;			
		
		ident =ctx.IDENT().getText();
		for(int i= ident.length(); i<11;i++)
			ident += " ";
		params += newTexts.get(ctx.params());
		compound_stmt = newTexts.get(ctx.compound_stmt());
		if(local_var_cnt > 0)
			System.out.print(ident+"proc"+blink_2+local_var_cnt+blink_2+block_level+blink_2+lexical_level+"\n"+params+compound_stmt+blink_11+"ret\n"+blink_11+"end\n");
		else
			System.out.print(ident+"nop\n"+params+compound_stmt+blink_11+"ret\n"+blink_11+"end\n");
		block_level--;
		newTexts.put(ctx, blink_11+"ldp\n"+blink_11+"call"+blink_3+ident);
		param_cnt = 0;
	}
	@Override public void exitParams(MiniCParser.ParamsContext ctx) 
	{
		String param = newTexts.get(ctx.param(0));
		int param_cnt = ctx.param().size();
		for(int i = 1; i<param_cnt ; i++)
			param += newTexts.get(ctx.param(i));
		if (ctx.getChildCount() == 0)
			newTexts.put(ctx, "");
		else if(ctx.getChild(0).getText().equals("void")){
			newTexts.put(ctx, "");
		}
		else { 
			newTexts.put(ctx, param);
		}
	}
	@Override public void enterParam(MiniCParser.ParamContext ctx)
	{
		param_cnt++;
	}
	@Override public void exitParam(MiniCParser.ParamContext ctx) 
	{ 
		String ident = null;
		ident = ctx.IDENT().getText();
		if(ctx.getChildCount() == 2){
			newTexts.put(ctx, blink_11+"sym"+blink_3+block_level+blink_2+param_cnt+blink_2+"1"+"\n");
			map.put(ident, block_level+blink_2+param_cnt);
		}
		else {
			// 이거는 할당을 할 방법이 없네요. 
			newTexts.put(ctx, "");
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
		newTexts.put(ctx, expr);
	}
	@Override public void enterWhile_stmt(MiniCParser.While_stmtContext ctx) 
	{
		block_level++;
		param_cnt = 0;
	}
	@Override public void exitWhile_stmt(MiniCParser.While_stmtContext ctx)
	{ 
		String _while = "", expr = "", stmt = "";

		_while = "$$"+(++index);
		String mark = _while;
		String mark2 = "$$"+(++index);
		String mark2_blink = mark2;
		for(int i = _while.length(); i<11;i++)
			_while += " ";
		for(int i = mark2.length(); i<11;i++)
			mark2_blink += " ";
		_while +="nop\n";
		expr = newTexts.get(ctx.expr())+blink_11+"fjp"+blink_3+mark2+"\n";
		stmt = newTexts.get(ctx.stmt());
		newTexts.put(ctx, _while+expr+stmt+blink_11+"ujp"+blink_3+mark+"\n"+mark2_blink+"nop\n");
		block_level--;
	}
	@Override public void enterCompound_stmt(MiniCParser.Compound_stmtContext ctx) 
	{
		pre_local.add(local_var_cnt);
		pre_local_var_cnt ++;
		local_var_cnt = 0;
		local_var_cnt += param_cnt;
	}
	@Override public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) 
	{ 
		lexical_level = 2;
		String local_decl = newTexts.get(ctx.local_decl(0)), stmt = newTexts.get(ctx.stmt(0));
	
		int local_cnt = ctx.local_decl().size(),stmt_cnt = ctx.stmt().size();
		
		for(int i = 1; i<local_cnt ; i++)
			local_decl += newTexts.get(ctx.local_decl(i));
		for(int i = 1; i<stmt_cnt ; i++){
			stmt += newTexts.get(ctx.stmt(i));
		}
		if(local_cnt == 0 && stmt_cnt == 0){
			newTexts.put(ctx, "");
		}
		else if(local_cnt !=0 && stmt_cnt == 0){
			if(ctx.parent instanceof MiniCParser.Fun_declContext)
				newTexts.put(ctx, local_decl);
			else
				newTexts.put(ctx, blink_11+"proc"+blink_2+local_var_cnt+blink_2+block_level+blink_2+lexical_level+"\n"+local_decl);
		}
		else if(local_cnt ==0 && stmt_cnt != 0){
			newTexts.put(ctx, stmt);
		}
		else {
			if(ctx.parent instanceof MiniCParser.Fun_declContext)
				newTexts.put(ctx, local_decl+stmt);
			else
				newTexts.put(ctx, blink_11+"proc"+blink_2+local_var_cnt+blink_2+block_level+blink_2+lexical_level+"\n"+local_decl+stmt);
		}
		if(pre_local_var_cnt > 1){
			local_var_cnt = pre_local.get(pre_local_var_cnt-1);
			pre_local_var_cnt --;
		}
	}
	@Override public void enterLocal_decl(MiniCParser.Local_declContext ctx)
	{
		String ident = null;
		ident = ctx.IDENT().getText();
		if(ctx.getChildCount() == 3){
			local_var_cnt++;
			String map_val = block_level+blink_2+global_cnt;
			map.put(ident, map_val);
		} else{
			int size = Integer.parseInt(ctx.LITERAL().getText());
			int start ;
			local_var_cnt += size;
			start = local_var_cnt - size+1;
			String map_val = block_level+blink_2+start;
			map.put(ident, map_val);
		}
	}
	@Override public void exitLocal_decl(MiniCParser.Local_declContext ctx) 
	{ 
		String ident = null;
		ident = ctx.IDENT().getText();
		if(ctx.getChildCount() == 3){
			newTexts.put(ctx, blink_11+"sym"+blink_3+block_level+blink_2+local_var_cnt+blink_2+"1"+"\n");
			map.put(ident, block_level+blink_2+local_var_cnt);
		}
		else {
			int size = Integer.parseInt(ctx.LITERAL().getText());
			int start = local_var_cnt - size+1;
			newTexts.put(ctx, blink_11+"sym"+blink_3+block_level+blink_2+start+blink_2+size+"\n");
			map.put(ident, block_level+blink_2+start);
		}
	}
	@Override public void enterIf_stmt(MiniCParser.If_stmtContext ctx) 
	{
		block_level++;
		param_cnt=0;
	}
	@Override public void exitIf_stmt(MiniCParser.If_stmtContext ctx) 
	{ 
		
		String s1 = null, s2 = null, s3 = null;
		
		if(ctx.getChildCount() == 5){
			s1 = "$$"+(++index);
			s2 = "$$"+(++index);
			s3 = s2;
			for(int i = s1.length(); i<11;i++)
				s1 += " ";
			for(int i = s2.length(); i<11;i++)
				s3 += " ";
			s1 +="nop\n";
			String expr = newTexts.get(ctx.expr())+blink_11+"fjp"+blink_3+s2+"\n";
			String stmt = newTexts.get(ctx.stmt(0));
			
			newTexts.put(ctx,s1+expr+stmt+s3+"nop\n");
			block_level--;
		}
		else if (ctx.getChildCount() == 7 && ctx.getChild(5) == ctx.ELSE()){
			s1 = "$$"+(++index);
			s2 = "$$"+(++index);
			s3 = s2;
			for(int i = s1.length(); i<11;i++)
				s1 += " ";
			for(int i = s2.length(); i<11;i++)
				s3 += " ";
			s1 +="nop\n";
			String expr = newTexts.get(ctx.expr())+blink_11+"fjp"+blink_3+s2+"\n";
			String stmt = newTexts.get(ctx.stmt(0));
			String stmt2 = newTexts.get(ctx.stmt(1));
			
			newTexts.put(ctx,s1+expr+stmt+s3+"nop\n"+blink_11+stmt2);
			block_level--;
		}
	}
	@Override public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) 
	{ 
		String ex1 = newTexts.get(ctx.expr());
		if(ctx.getChildCount() == 2){
			newTexts.put(ctx, "");
		}
		else{
			newTexts.put(ctx,  blink_11+"lod"+blink_3+ex1+"\n");
		}
	}
	@Override public void exitExpr(MiniCParser.ExprContext ctx) { 
		String expr1 = null, expr2 = null, op = null;
		String ident  = null;
		
		// 이진 연산 or assignment
		if(isBinaryOperation(ctx))
		{
			// assignment
			if(ctx.getChild(0) == ctx.IDENT()){
				ident = map.get(ctx.IDENT().getText());
				expr1 = newTexts.get(ctx.expr(0));
				if(ctx.expr(0).getChildCount() == 1){
					if(map.get(expr1) != null){
						newTexts.put(ctx, blink_11+"lod"+blink_3+map.get(expr1)+"\n"+blink_11+"str"+blink_3+ident+"\n");
					}
					else {
						newTexts.put(ctx, blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"str"+blink_3+ident+"\n");
					}
				}
				else{
					if(ctx.expr(0).getChildCount() == 4){
						newTexts.put(ctx, expr1+blink_11+"str"+blink_3+ident+"\n");
					}
					else
						newTexts.put(ctx, expr1+blink_11+"str"+blink_3+ident+"\n");
				}
			}else {	// 이진연산.
				expr1 = newTexts.get(ctx.expr(0));
				String expr1_str = map.get(expr1);
				expr2 = newTexts.get(ctx.expr(1));
				String expr2_str = map.get(expr2);
				op = ctx.getChild(1).getText();
				String op_comm = null;
				if(op.equals("+")){
					op_comm = "add";
				} else if(op.equals("-")){
					op_comm = "sub";
				} else if(op.equals("*")){
					op_comm = "mult";
				} else if(op.equals("/")){
					op_comm = "div";
				} else if(op.equals("%")){
					op_comm = "mod";
				} else if(op.equals("or")){
					op_comm = "or";
				} else if(op.equals("and")){
					op_comm = "and";
				} else if(op.equals("<=")){
					op_comm = "le";
				} else if(op.equals(">=")){
					op_comm = "ge";
				} else if(op.equals("==")){
					op_comm = "eq";
				} else if(op.equals("!=")){
					op_comm = "ne";
				} else if(op.equals("<")){
					op_comm = "lt";
				} else if(op.equals(">")){
					op_comm = "gt";
				}
				if(map.containsKey(expr1) == true && map.containsKey(expr2) == true)
					newTexts.put(ctx,blink_11+"lod"+blink_3+expr1_str+"\n"+blink_11+"lod"+blink_3+expr2_str+"\n"+blink_11+op_comm+"\n" );
				else if(map.containsKey(expr1) == false && map.containsKey(expr2) == true){
					if(ctx.expr(0).getChildCount() !=1)
						newTexts.put(ctx,expr1+blink_11+"lod"+blink_3+expr2_str+"\n"+blink_11+op_comm+"\n" );
					else
						newTexts.put(ctx,blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"lod"+blink_3+expr2_str+"\n"+blink_11+op_comm+"\n" );
				} else if(map.containsKey(expr1) == true && map.containsKey(expr2) == false){
					if(ctx.expr(1).getChildCount() !=1)
						newTexts.put(ctx,blink_11+"lod"+blink_3+expr1_str+"\n"+expr2_str+blink_11+op_comm+"\n" );
					else
						newTexts.put(ctx,blink_11+"lod"+blink_3+expr1_str+"\n"+blink_11+"ldc"+blink_3+expr2+"\n"+blink_11+op_comm+"\n" );
				} else{
					if(ctx.expr(0).getChildCount() !=1 && ctx.expr(1).getChildCount() !=1)
						newTexts.put(ctx,expr1+expr2+blink_11+op_comm+"\n" );
					else if(ctx.expr(0).getChildCount() !=1 && ctx.expr(1).getChildCount() ==1)
						newTexts.put(ctx,expr1+blink_11+"ldc"+blink_3+expr2+"\n"+blink_11+op_comm+"\n" );
					else if(ctx.expr(0).getChildCount() ==1 && ctx.expr(1).getChildCount() !=1)
						newTexts.put(ctx,blink_11+"ldc"+blink_3+expr1+"\n"+expr1+expr2+blink_11+op_comm+"\n" );
					else
						newTexts.put(ctx,blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"ldc"+blink_3+expr2+"\n"+blink_11+op_comm+"\n" );
				}
				
			}
		}
		else if(ctx.getChildCount() == 3 ){
			expr1 = newTexts.get(ctx.expr(0));
			newTexts.put(ctx, expr1);
		}
		else if (ctx.getChildCount() == 4 && ctx.getChild(2) instanceof MiniCParser.ExprContext){
			ident = ctx.IDENT().getText();
			String var_val = map.get(ident);
			String mString = "";
			expr1 = newTexts.get(ctx.expr(0));
			mString += blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"lod"+blink_3+var_val+"\n"+blink_11+"add\n";
			newTexts.put(ctx, mString);
		}
		else if (ctx.getChildCount() == 4 && ctx.getChild(2) instanceof MiniCParser.ArgsContext){
			ident = ctx.IDENT().getText();
			String expr1_str = "";
			for(int i = 0; i<ctx.args().expr().size();i++){
				expr1_str += blink_11+"lod"+blink_3+map.get(ctx.args().expr(i).getText())+"\n";
			}
			
			if(ctx.getChild(2).getChildCount() == 0)
			{
				newTexts.put(ctx, blink_11+"ldp\n"+blink_11+"call" + blink_3 + ident);
			} else{
				newTexts.put(ctx, blink_11+"ldp\n"+expr1_str+blink_11+"call" + blink_3 + ident+"\n");
			}
			
		}
		else if(ctx.getChildCount() == 2) {
			expr1 = newTexts.get(ctx.expr(0));
			if(ctx.getChild(0).getText().equals("!")){
				newTexts.put(ctx, blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"notop\n");
			}else if(ctx.getChild(0).getText().equals("-")){
				newTexts.put(ctx,blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"neg\n");
			}else {
				newTexts.put(ctx, blink_11+"ldc"+blink_3+expr1+"\n");
			}
		}
		else if(ctx.getChildCount() == 6){
			String mString = "";
			ident = ctx.IDENT().getText();
			String var_val = map.get(ident);
			// 배열의 인덱스
			expr1 = newTexts.get(ctx.expr(0));
			// 할당되는 수.
			expr2 = newTexts.get(ctx.expr(1));
			if(map.get(expr2) != null){
				expr2 = "\n"+blink_11+"lod"+blink_3+map.get(expr2);
			}
			mString += blink_11+"ldc"+blink_3+expr1+"\n"+blink_11+"lda"+blink_3+var_val+"\n"+blink_11+"add\n";
			mString += blink_11+"ldc"+blink_3+expr2+"\n"+blink_11+"sti\n";
			newTexts.put(ctx, mString);
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
			expr += newTexts.get(ctx.expr(i));
		newTexts.put(ctx, expr);
	}	

}
