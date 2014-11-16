package com.example.sdcardfiledemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText et;
    CheckBox cb;
    TextView tv,tv1;
    final static String FILENAME="mytextfile.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et=(EditText) findViewById(R.id.editText1);
		cb=(CheckBox) findViewById(R.id.checkBox1);
		tv=(TextView) findViewById(R.id.textView1);
		tv1=(TextView) findViewById(R.id.textView2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    public void WriteFile(View v){
    	String fileName="Sdcardfile-"+System.currentTimeMillis()+".txt";
		File dir = Environment.getExternalStorageDirectory();
		
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File newFile = new File(dir+"/"+fileName);
			FileOutputStream fos = null;
			try {
				newFile.createNewFile();
				if (newFile.exists()&&newFile.canWrite()){
					fos=new FileOutputStream(newFile);
					fos.write(et.getText().toString().getBytes());
					tv1.setText(fileName+"写入SD卡");
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if (fos!=null){
					try{
						fos.flush();
						fos.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}}

    	
    }
    public void Readfile(View v){
    	FileInputStream fis=null;
    	try {
			fis=openFileInput(FILENAME);
			if(fis.available()==0){
				return ;
			}
			byte[] readByte =new byte[fis.available()];
			while(fis.read(readByte)!=-1){}
			String text=new String(readByte);
			tv.setText(text);
			tv1.setText("文件读取成功，文件长度为"+text.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
    }
 }
