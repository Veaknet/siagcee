function fHoliday(y,m,d) {
	var rE=fGetEvent(y,m,d), r=null;
	if (m==1&&d==1){
		r=[" Jan 1, "+y+" \n Año Nuevo ",gsAction,"skyblue","red"];
	}else if (m==12&&d==25){
		r=[" Dec 25, "+y+" \n Navidad ",gsAction,"skyblue","red"];
	}
	return rE?rE:r;
}


