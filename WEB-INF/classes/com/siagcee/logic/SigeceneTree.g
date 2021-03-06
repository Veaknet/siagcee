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

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Date;
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
	|	^('pre' 
			{
				if(EstudioPerso.getInstance().is_initial()){
					$accionar = true;
				}else{
					$accionar = false;
				}
			}
			(stat[accionar])+
		)
	|	^('post' 
			{
				if(EstudioPerso.getInstance().is_final()){
					$accionar = true;
				}else{
					$accionar = false;
				}
			}
			(stat[accionar])+
		)
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
				try{
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date _aDate = df.parse($a.valor);
					Date _bDate = df.parse($b.valor);
					$valor = (_bDate.compareTo(_aDate) > 0) ? "1":"0";
				}catch(Exception fechas){
					try{
						$valor = (Double.parseDouble($a.valor) < Double.parseDouble($b.valor))?"1":"0";
					}catch(Exception numerica){
						$valor = ($b.valor.toLowerCase().compareTo($a.valor.toLowerCase()) > 0)?"1":"0";
					}
				}
			}
	|	^('<=' a=expr b=expr)
			{
				try{
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date _aDate = df.parse($a.valor);
					Date _bDate = df.parse($b.valor);
					$valor = (_bDate.compareTo(_aDate) > 0 || _aDate.compareTo(_bDate) == 0 ) ? "1":"0";
				}catch(Exception fechas){
					try{
						$valor = (Double.parseDouble($a.valor) <= Double.parseDouble($b.valor))?"1":"0";
					}catch(Exception numerica){
						$valor = (($b.valor.toLowerCase().compareTo($a.valor.toLowerCase()) > 0) || ($b.valor.toLowerCase().compareTo($a.valor.toLowerCase()) == 0))?"1":"0";
					}
				}
			}
	|	^('>' a=expr b=expr)
			{
				try{
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date _aDate = df.parse($a.valor);
					Date _bDate = df.parse($b.valor);
					$valor = (_aDate.compareTo(_bDate) > 0) ? "1":"0";
				}catch(Exception fechas){
					try{
						$valor = (Double.parseDouble($a.valor) > Double.parseDouble($b.valor))?"1":"0";
					}catch(Exception numerica){
						$valor = ($a.valor.toLowerCase().compareTo($b.valor.toLowerCase()) > 0)?"1":"0";
					}
				}
			}
	|	^('>=' a=expr b=expr)
			{
				try{
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date _aDate = df.parse($a.valor);
					Date _bDate = df.parse($b.valor);
					$valor = (_aDate.compareTo(_bDate) > 0 || _bDate.compareTo(_aDate) == 0 ) ? "1":"0";
				}catch(Exception fechas){
					try{
						$valor = (Double.parseDouble($a.valor) >= Double.parseDouble($b.valor))?"1":"0";
					}catch(Exception numerica){
						$valor = (($a.valor.toLowerCase().compareTo($b.valor.toLowerCase()) > 0) || ($a.valor.toLowerCase().compareTo($b.valor.toLowerCase()) == 0))?"1":"0";
					}
				}
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
	|	redondea
			{
				$valor = $redondea.valor;
			}
	|	diff_fechas
			{
				$valor = $diff_fechas.valor;
			}
	|	concatenar
			{
				$valor = $concatenar.valor;
			}
	;

promedio returns [String valor]
	:	^('promedio' ID)
		{
			//para permitir que se compartan las variables entre los usuarios
			/*String v = (String)memory.get("promedio(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getPromedioField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("promedio(["+$ID.text+"])", $valor);
			}*/
			try{
				$valor = EstudioPerso.getInstance().getPromedioField($ID.text);
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

sumatoria returns [String valor]
	:	^('sumatoria' ID)
		{
			//para permitir que se compartan las variables entre los usuarios
			/*String v = (String)memory.get("sumatoria(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getSumatoriaField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("sumatoria(["+$ID.text+"])", $valor);
			}*/
			try{
				$valor = EstudioPerso.getInstance().getSumatoriaField($ID.text);
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

contar returns [String valor]
	:	^('contar' ID)
		{
			//para permitir que se compartan las variables entre los usuarios
			/*String v = (String)memory.get("contar(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getContarField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("contar(["+$ID.text+"])", $valor);
			}*/
			try{
				$valor = EstudioPerso.getInstance().getContarField($ID.text);
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

max returns [String valor]
	:	^('max' ID)
		{
			//para permitir que se compartan las variables entre los usuarios
			/*String v = (String)memory.get("max(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getMaxField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("max(["+$ID.text+"])", $valor);
			}*/
			try{
				$valor = EstudioPerso.getInstance().getMaxField($ID.text);
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

min returns [String valor]
	:	^('min' ID)
		{
			//para permitir que se compartan las variables entre los usuarios
			/*String v = (String)memory.get("min(["+$ID.text+"])");
			if (v != null){
				$valor = v;
			}else{
				try{
					$valor = EstudioPerso.getInstance().getMinField($ID.text);
				}catch(Exception proEx){
					throw new RecognitionException();
				}
				memory.put("min(["+$ID.text+"])", $valor);
			}*/
			try{
				$valor = EstudioPerso.getInstance().getMinField($ID.text);
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

redondea returns [String valor]
	:	^('redondea' expr)
		{
			try{
				$valor = String.valueOf(Math.round(Double.parseDouble($expr.valor)));
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

diff_fechas returns [String valor]
	:	^('diff_fechas' a=expr b=expr)
		{
			try{
				$valor = EstudioPerso.getInstance().diff_fechas($a.valor, $b.valor);
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;

concatenar returns [String valor]
	:	^('concatenar' a=expr b=expr)
			{
			try{
				$valor = $a.valor+$b.valor;
			}catch(Exception proEx){
				throw new RecognitionException();
			}
		}
	;
