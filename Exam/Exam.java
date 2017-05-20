import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class Exam implements ActionListener
{
	JProgressBar pbar;
	int number=0,que[]=new int[9],k=0;
	HashSet s;
	String ans[]=new String[9];
	String as[]=new String[9];
	JFrame f,result;
	JDesktopPane desktop;
	JButton prev,next,finish;
	JLabel l,l1,l2,l3,l4;
	JPanel jp1,jp2,jp3,jp4;
	JCheckBox jcb1,jcb2,jcb3,jcb4;
	JRadioButton jrb1,jrb2,jrb3,jrb4;
	int rflag=0,cflag=0,finishflag=0,reviewflag=0;;
	public Exam()
	{
	    f=new JFrame();
		f.setSize(400,400);
		f.setLayout(new BorderLayout(3,1));
	    jp1=new JPanel();
		f.add(jp1,"North");
		jp2=new JPanel();
		f.add(jp2,"Center");	 
		jp3=new JPanel();
		f.add(jp3,"South");		
		jp4=new JPanel();
		f.add(jp4,"West");
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		l=new JLabel();
		jp1.add(l);
		jp4.setLayout(new GridLayout(4,1));
		JLabel l1=new JLabel("A.");
		jp4.add(l1);
		l2=new JLabel("B.");
		jp4.add(l2);
	    l3=new JLabel("C.");
		jp4.add(l3);
		l4=new JLabel("D.");
		jp4.add(l4);
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
		prev=new JButton("Prev");
		prev.addActionListener(this);
		jp3.add(prev);
		prev.setVisible(false);
		next=new JButton("Next");
		next.addActionListener(this);
		jp3.add(next);
		next.setVisible(false);
		finish=new JButton("Finish");
		finish.addActionListener(this);
		finish.setVisible(false);
		generateNUM();
		changebutton();
	}
	public void generateNUM()
	{
		    s=new HashSet();
		    s.add(number);
		    for(int i=0;i<que.length; )
		    {
		    	while(s.contains(number))
			    number=(int)(Math.random()*100);
			    if(number<100){
			    	s.add(number);
			        que[i++]=number;
			    } 
		    }
	}
	public void changebutton()
	{
		if(k==0)
		{
			prev.setVisible(false);
			next.setVisible(true);
			finish.setVisible(false);
		}
		else if(k==(que.length-1))
		  {
		  	if(finishflag==0)
		  	finish.setVisible(true);
			next.setVisible(false);
			prev.setVisible(true);
		    jp3.add(finish);
		  }
		else
		{
			    finish.setVisible(false);
				prev.setVisible(true);	
				next.setVisible(true);
		}	  
		number=que[k];
		    //-----------
		String que="",a="",b="",c="",d="",mul="";
	      try
			{
				 Class.forName("com.mysql.jdbc.Driver");
				 String url="jdbc:mysql://localhost:3306/test?user=root&password=root";
			     Connection con=DriverManager.getConnection(url);
			     Statement stmt=con.createStatement();
			     ResultSet rs=stmt.executeQuery("SELECT question,a,b,c,d,multiple FROM exam where q_no='"+number+"'");
			     rs.next();
			     que=rs.getString("question");
			     a=rs.getString("a");
			     b=rs.getString("b");
			     c=rs.getString("c");
			     d=rs.getString("d");
			     mul=rs.getString("multiple");
			     l.setText("Q:"+que);
			}
			catch(Exception n)
			{
				  n.printStackTrace();
			      System.out.println(n.getMessage());	
			}
		//-------
	     jp2.setLayout(new GridLayout(4,1));
		if(mul.equalsIgnoreCase("no"))
		{
	         if(cflag==1)
	         {
	          jp2.remove(jcb1);
	          jp2.remove(jcb2);
	          jp2.remove(jcb3);
	          jp2.remove(jcb4);
	         }
	         if(rflag==1)
	         {
	          jp2.remove(jrb1);
	          jp2.remove(jrb2);
	          jp2.remove(jrb3);
	          jp2.remove(jrb4);
	         }
	         
	         if(ans[k]==null)
	         {
	          jrb1=new JRadioButton(a,false);
	          jrb2=new JRadioButton(b,false);
	          jrb3=new JRadioButton(c,false);
	          jrb4=new JRadioButton(d,false);
	         }
	         else
	         {
	         	boolean b1=false,b2=false,b3=false,b4=false;
	         	for(int i=0;i<ans[k].length();i++)
	         	{
	         		if(ans[k].charAt(i)=='a')
	         		{
	         			b1=true;
	         		}
	         		if(ans[k].charAt(i)=='b')
	         		{
	         			b2=true;
	         		}
	         		if(ans[k].charAt(i)=='c')
	         		{
	         			b3=true;
	         		}
	         		if(ans[k].charAt(i)=='d')
	         		{
	         			b4=true;
	         		}
	         	}
	          jrb1=new JRadioButton(a,b1);
	          jrb2=new JRadioButton(b,b2);
	          jrb3=new JRadioButton(c,b3);
	          jrb4=new JRadioButton(d,b4);
	         }
	         ButtonGroup group=new ButtonGroup();
	         group.add(jrb1); 
	         group.add(jrb2);
	         group.add(jrb3);
	         group.add(jrb4);
	         
	         rflag=1;
	         cflag=0;
	         jp2.add(jrb1);
	         jp2.add(jrb2);
	         jp2.add(jrb3);
	         jp2.add(jrb4);
	         
	         if(reviewflag==1)
	         {
	         	jrb1.setEnabled(false);
		     	jrb2.setEnabled(false);
		     	jrb3.setEnabled(false);
		     	jrb4.setEnabled(false);
		     		changeColor();
	         }
	         
	         
		}
		else
		{
		     if(cflag==1)
	         {
	          jp2.remove(jcb1);
	          jp2.remove(jcb2);
	          jp2.remove(jcb3);
	          jp2.remove(jcb4);
	         }
		     if(rflag==1)
		     {
		      jp2.remove(jrb1);
	          jp2.remove(jrb2);
	          jp2.remove(jrb3);
	          jp2.remove(jrb4);
		     }	
		     if(ans[k]==null)
		     {
		     jcb1=new JCheckBox(a,false);
		     jcb2=new JCheckBox(b,false);
		     jcb3=new JCheckBox(c,false);
      	     jcb4=new JCheckBox(d,false);
		     }
		     else
		     {
	         	boolean b1=false,b2=false,b3=false,b4=false;
	         	for(int i=0;i<ans[k].length();i++)
	         	{
	         		if(ans[k].charAt(i)=='a')
	         		{
	         			b1=true;
	         		}
	         		if(ans[k].charAt(i)=='b')
	         		{
	         			b2=true;
	         		}
	         		if(ans[k].charAt(i)=='c')
	         		{
	         			b3=true;
	         		}
	         		if(ans[k].charAt(i)=='d')
	         		{
	         			b4=true;
	         		}
	         	}
	             jcb1=new JCheckBox(a,b1);
	             jcb2=new JCheckBox(b,b2);
	             jcb3=new JCheckBox(c,b3);
	             jcb4=new JCheckBox(d,b4);
		     }
      	      cflag=1;
      	      rflag=0;
      	     jp2.add(jcb1);
		     jp2.add(jcb2);
		     jp2.add(jcb3);
		     jp2.add(jcb4);
		     
		     if(reviewflag==1)
		     {
		     	jcb1.setEnabled(false);
		     	jcb2.setEnabled(false);
		     	jcb3.setEnabled(false);
		     	jcb4.setEnabled(false);
		     	changeColor();
		     }
	         
	         
		}
		f.setVisible(true);	
	}
	public void answer(int k)
	{
			ans[k]="";
			if(cflag==1)
			{
			 if(jcb1.isSelected())
			 {
			 	jcb1.setSelected(true);
			 	ans[k]+="a";
			 }
	         if(jcb2.isSelected())
	         {
	         	jcb2.setSelected(true);
	         	 ans[k]+="b";
	         }
			 if(jcb3.isSelected())
	         {
	         	jcb3.setSelected(true);
	         	ans[k]+="c";
	         }
		     if(jcb4.isSelected())
		     {
		     	jcb4.setSelected(true);
		     	ans[k]+="d";
		     }  
			}
			if(rflag==1)
			{
			 if(jrb1.isSelected())
			 {
			 	jrb1.setSelected(true);
			 	ans[k]+="a";
			 }
	   	     if(jrb2.isSelected())
	   	     {
	   	     	jrb2.setSelected(true);
	   	     	ans[k]+="b";
	   	     }
	   	     if(jrb3.isSelected())
	   	     {
	   	       jrb3.setSelected(true);
	   	       ans[k]+="c";
	   	     }
	   	     if(jrb4.isSelected())
	   	     {
	   	       jrb4.setSelected(true);
	   	       ans[k]+="d"; 
	   	     }  
			}
	}
	public void actionPerformed(ActionEvent e)
	{
		String str=e.getActionCommand();
		System.out.println(str+" was clicked");
		if(str.equals("Next"))
		{
			answer(k); 	 	
			k++;
			changebutton();
		}
		else if(str.equals("Prev"))
		{
		    answer(k);
			k=k-1;
		    changebutton();	
		}
		else if(str.equals("Finish"))
		{
			finishflag=1;
			finish.setVisible(false);
			//--------------------
			answer(k);
			//--------------------
			//--------------
			for(int j=0;j<que.length;j++)
			{
				number=que[j];
				try
			   {
				 Class.forName("com.mysql.jdbc.Driver");
				 String url="jdbc:mysql://localhost:3306/test?user=root&password=root";
			     Connection con=DriverManager.getConnection(url);
			     Statement stmt=con.createStatement();
			     ResultSet rs=stmt.executeQuery("SELECT answer FROM exam where q_no='"+number+"'");
			     if(rs.next())
			     {
			     	as[j]=rs.getString("answer");
			     }
			   }
			   catch(Exception n)
			   {
				  n.printStackTrace();
			      System.out.println(n.getMessage());	
			   }
			}
			int marks=0;
			int total=9;
			for(int i=0;i<que.length;i++)
			{
			    if(ans[i].equalsIgnoreCase(as[i]))
					marks=marks+1;
					System.out.println("marks="+marks);
			}
			float percentage=((float)marks/(float)total)*100;
			System.out.println(percentage);
			//---------------
			result=new JFrame();
			result.setSize(200,200);
			result.setResizable(false);
			result.setLayout(new FlowLayout());
			pbar=new JProgressBar(0,100);
			pbar.setValue(((int)percentage));
			pbar.setStringPainted(true);
		    result.add(pbar);
			JButton exit=new JButton("Exit");
			exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(1);
				}
			});
		     result.add(exit);
				JButton review=new JButton("Review");
				review.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						reviewflag=1;
						f.toFront();
						if(cflag==1)
						{
						  jcb1.setEnabled(false);
		     	          jcb2.setEnabled(false);
		     	          jcb3.setEnabled(false);
		     	          jcb4.setEnabled(false);	
						}
						else
						{
							jrb1.setEnabled(false);
		     	            jrb2.setEnabled(false);
		     	            jrb3.setEnabled(false);
		     	            jrb4.setEnabled(false);
						}
					    changeColor();
					}
				});
				result.add(review);
		        result.setVisible(true);
		}
	}
	public void changeColor()
	{
		 for(int i=0;i<as[k].length();i++)
		  {
			if(as[k].charAt(i)=='a')
			{
			   if(cflag==1)
			  {
			  jcb1.setBackground(Color.green);
			  }
			  if(rflag==1)
			  {
			  jrb1.setBackground(Color.green);
			  }
			}
			if(as[k].charAt(i)=='b')
			{
				if(cflag==1)
				{
				  jcb2.setBackground(Color.green);
				}
				if(rflag==1)
				{
				jrb2.setBackground(Color.green);
				}
			}
			if(as[k].charAt(i)=='c')
			{
				if(cflag==1)
				{
				jcb3.setBackground(Color.green);
				}
			    if(rflag==1)
				{
				 jrb3.setBackground(Color.green);
				}
			}
			if(as[k].charAt(i)=='d')
			{
				if(cflag==1)
				{
				  jcb4.setBackground(Color.green);
				}
				if(rflag==1)
				{
				   jrb4.setBackground(Color.green);
				}
			}
		  }
		if(!(ans[k].equals(as[k])))
		{
			for(int i=0;i<ans[k].length();i++)
		  {
			if(ans[k].charAt(i)=='a')
			{
			   if(cflag==1)
			  {
			  jcb1.setBackground(Color.red);
			  }
			  if(rflag==1)
			  {
			  jrb1.setBackground(Color.red);
			  }
			}
			if(ans[k].charAt(i)=='b')
			{
				if(cflag==1)
				{
				  jcb2.setBackground(Color.red);
				}
				if(rflag==1)
				{
				jrb2.setBackground(Color.red);
				}
			}
			if(ans[k].charAt(i)=='c')
			{
				if(cflag==1)
				{
				jcb3.setBackground(Color.red);
				}
			    if(rflag==1)
				{
				 jrb3.setBackground(Color.red);
				}
			}
			if(ans[k].charAt(i)=='d')
			{
				if(cflag==1)
				{
				  jcb4.setBackground(Color.red);
				}
				if(rflag==1)
				{
				   jrb4.setBackground(Color.red);
				}
			}
		}
		}
	}
	public static void main(String args[])
	{
		Exam exam=new Exam(); 
	}
}