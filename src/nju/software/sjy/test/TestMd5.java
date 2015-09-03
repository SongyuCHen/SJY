package nju.software.sjy.test;

import nju.software.sjy.webservice.util.Base64Util;

public class TestMd5 {
	public static void main(String args[]){
		String md5 = "5YaF6YOo6ZSZ6K+v";
		String re = Base64Util.decode(md5);
		System.out.println(re);
	}

}
