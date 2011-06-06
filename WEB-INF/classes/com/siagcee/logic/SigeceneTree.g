tree grammar SigeceneTree;

options {
	language=Java;
	memoize=true;
	backtrack=true;
	tokenVocab=SigeceneGram;
	ASTLabelType=CommonTree;
}

@header {
package com.siagcee.logic;

import java.util.HashMap;
}

@members {
HashMap memory = EstudioPerso.getInstance()._memoriaInternaEstudio;
}

prog	:	stat[true]+;

stat[boolean accionar]
	:	condicional[accionar]
	|	^('obtener' proposicion) 
			{
				if($accionar){
					EstudioPerso.getInstance().addResultado($proposicion.valor);
				}
			}
	|	^('=' ID proposicion)
			{
				if($accionar){
					memory.put($ID.text, $proposicion.valor);
				}
			}
	;

condicional[boolean accionar]
	:	^('si' cond=proposicion 
			{
				if(($cond.valor).equals("1")){
					$accionar = true;
				}else{
					$accionar = false;
				}
			}
			(stat[accionar])+
		)
	;

proposicion returns [String valor]
	:	^('<' a=expr b=expr)
			{
				$valor = ($b.valor.toLowerCase().compareTo($a.valor.toLowerCase()) > 0)?"1":"0";
				//$valor = (Double.parseDouble($a.valor) < Double.parseDouble($b.valor))?"1":"0";
			}
	|	^('<=' a=expr b=expr)
			{
				$valor = (($b.valor.toLowerCase().compareTo($a.valor.toLowerCase()) > 0) || ($b.valor.toLowerCase().compareTo($a.valor.toLowerCase()) == 0))?"1":"0";
				//$valor = (Double.parseDouble($a.valor) <= Double.parseDouble($b.valor))?"1":"0";
			}
	|	^('>' a=expr b=expr)
			{
				$valor = ($a.valor.toLowerCase().compareTo($b.valor.toLowerCase()) > 0)?"1":"0";
				//$valor = (Double.parseDouble($a.valor) > Double.parseDouble($b.valor))?"1":"0";
			}
	|	^('>=' a=expr b=expr)
			{
				$valor = (($a.valor.toLowerCase().compareTo($b.valor.toLowerCase()) > 0) || ($a.valor.toLowerCase().compareTo($b.valor.toLowerCase()) == 0))?"1":"0";
				//$valor = (Double.parseDouble($a.valor) >= Double.parseDouble($b.valor))?"1":"0";
			}
	|	^('==' a=expr b=expr)
			{
				$valor = (($a.valor.toLowerCase()).equals($b.valor.toLowerCase()))?"1":"0";
			}
	|	^('<>' a=expr b=expr)
			{
				$valor = (($a.valor.toLowerCase()).equals($b.valor.toLowerCase()))?"0":"1";
			}
	|	^('Y' a=proposicion b=proposicion)
			{
				$valor = (($a.valor).equals("1") && ($b.valor).equals("1"))?"1":"0";
			}
	|	^('O' a=proposicion b=proposicion)
			{
				$valor = (($a.valor).equals("1") || ($b.valor).equals("1"))?"1":"0";
			}
	|	^('NO' a=proposicion)
			{
				if($a.valor.equals("1")){
					$valor = "0";
				}else{
					$valor = "1";
				}
			}
	|	BOOLEAN 
			{
				$valor = "0";
				if(($BOOLEAN.text).equals("verdad")){
					$valor = "1";
				}
			}
	|	expr 
			{
				$valor = $expr.valor;
			}
	;

expr returns [String valor]
	:	^('+' a=expr b=expr)	
			{
				$valor = String.valueOf(Double.parseDouble($a.valor) + Double.parseDouble($b.valor));
			}
	|	^('-' a=expr b=expr)
			{
				$valor = String.valueOf(Double.parseDouble($a.valor) - Double.parseDouble($b.valor));
			}
	|	^('*' a=expr b=expr)
			{
				$valor = String.valueOf(Double.parseDouble($a.valor) * Double.parseDouble($b.valor));
			}
	|	^('/' a=expr b=expr)
			{
				$valor = String.valueOf(Double.parseDouble($a.valor) / Double.parseDouble($b.valor));
			}
	|	funcPredefinidas
			{
				$valor = $funcPredefinidas.valor;
			}
	|	ID	
			{
				String v = (String)memory.get($ID.text);
				if (v != null){
					$valor = v;
				}else{
					memory.put($ID.text, "");
				}
			}
	|	DOUBLE	
			{
				$valor = new String($DOUBLE.text);
			}
	|	STRING	
			{
				//Elimino las comillas dobles del texto del String
				$valor = new String($STRING.text);
				$valor = $valor.substring(1,$valor.length()-1);
			}
	;

//funciones predefinidas
funcPredefinidas returns [String valor]
	:	promedio
			{
				$valor = $promedio.valor;
			}
	|	sumatoria
			{
				$valor = $sumatoria.valor;
			}
	|	contar
			{
				$valor = $contar.valor;
			}
	|	max
			{
				$valor = $max.valor;
			}
	|	min
			{
				$valor = $min.valor;
			}
	;

promedio returns [String valor]
	:	^('promedio' ID)
		{
			String v = (String)memory.get("promedio(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getPromedioField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("promedio(["+$ID.text+"])", $valor);
			}
		}
	;

sumatoria returns [String valor]
	:	^('sumatoria' ID)
		{
			String v = (String)memory.get("sumatoria(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getSumatoriaField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("sumatoria(["+$ID.text+"])", $valor);
			}
		}
	;

contar returns [String valor]
	:	^('contar' ID)
		{
			String v = (String)memory.get("contar(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getContarField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("contar(["+$ID.text+"])", $valor);
			}
		}
	;

max returns [String valor]
	:	^('max' ID)
		{
			String v = (String)memory.get("max(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getMaxField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("max(["+$ID.text+"])", $valor);
			}
		}
	;

min returns [String valor]
	:	^('min' ID)
		{
			String v = (String)memory.get("min(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getMinField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("min(["+$ID.text+"])", $valor);
			}
		}
	;


