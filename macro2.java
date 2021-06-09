/*
SIMPLE SINGLE PASS MACRO PROCESSOR
SUBMITTED BY:VANSHIKA
ROLL NO:1913162
ID:BTBTC19046
B.Tech CS -C
*/
//instructions for input: after END add an extra line with '.' as a character in it

import java.io.*;
import java.util.*;
class macro2
{
    //for main control
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(new File("macrotest2.txt")).useDelimiter("\n");
		PrintWriter mdxt=new PrintWriter(new BufferedWriter(new FileWriter("MDT.txt")));
		PrintWriter mnxt=new PrintWriter(new BufferedWriter(new FileWriter("MNT.txt")));
		mdxt.close();
		mnxt.close();
		
		PrintWriter op=new PrintWriter(new BufferedWriter(new FileWriter("Output.txt")));
		ArrayList<String> ala1= new ArrayList<String>();
		ArrayList<String> ala2 = new ArrayList<String>();
		int mdi[]=new int[1];
		int mdtp[]=new int[1];
		mdi[0]=0;
		mdtp[0]=0;
		int mdtc=1,mntc=1,mdlc=0,go=1;
		do
		{
			int flag=0;
			String source_card=read(sc,mdi,mdtp,mdlc,ala1);
			Scanner mnt_r=new Scanner(new File("MNT.txt")).useDelimiter("\n|\\ ");
			Scanner s=new Scanner(source_card).useDelimiter(" ");
			String first=s.next();
			while(mnt_r.hasNext())
			{
				if(first.equalsIgnoreCase(mnt_r.next()))
				{
					mdi[0]=1;
					String i=mnt_r.next();
					mdtp[0]=Integer.parseInt(i.substring(0,i.length()-1));
					Scanner s1=new Scanner((s.next())).useDelimiter(",");
					while(s1.hasNext())
					{
						ala1.add(s1.next());
					}
					flag=1;
					break;
				}
				else
					mnt_r.next();
			}
			if(flag==0)
			{
				if(first.equalsIgnoreCase("MACRO"))
				{
					PrintWriter mdt=new PrintWriter(new BufferedWriter(new FileWriter("MDT.txt",true)));
					PrintWriter mnt=new PrintWriter(new BufferedWriter(new FileWriter("MNT.txt",true)));
					String m_n_line=read(sc,mdi,mdtp,mdlc,ala1);
					Scanner s1=new Scanner(m_n_line).useDelimiter(" ");
					String mac_name=s1.next();
					mnt.println(mac_name+" "+mdtc);
					mntc=mntc+1;
					Scanner s2=new Scanner(m_n_line).useDelimiter(" |\\,");
					s2.next();
					while(s2.hasNext())
					{
						ala2.add(s2.next());
					}
					mdt.print(mdtc+" "+mac_name+" ");
					String l="";
					while(s1.hasNext())
						l=l+(s1.next()+",");
					mdt.println(l.substring(0,l.length()-1));
					mdtc++;
					mdlc++;
					while(true)
					{
						String line=read(sc,mdi,mdtp,mdlc,ala1);
						String fin=mdtc+" ";
						Scanner s3=new Scanner(line).useDelimiter(" |\\,");
						while(s3.hasNext())
						{
							String temp=s3.next();
							for(int i=0;i<ala2.size();i++)
							{
								if(temp.equalsIgnoreCase(ala2.get(i)))
								{
									temp="#"+i;
								}
							}
							fin =fin+temp+" ";
						}
						mdt.println(fin);
						mdtc++;
						if(line.equalsIgnoreCase("MACRO"))
							mdlc++;
						else if(line.equalsIgnoreCase("MEND"))
						{
							mdlc-=1;
							if(mdlc==0)
							{
								ala2.clear();
								mdt.close();
								mnt.close();
								break;
							}
						}
					}
				}
				else
				{
					op.println(source_card);
					if(source_card.equalsIgnoreCase("END"))
					{
						go=0;
						op.close();
						break;
					}
				}
			}

		}while(go==1);
		System.out.println("OUTPUT RECIEVED");
	}
	//to read for source code 
	public static String read(Scanner sc,int mdi[],int mdtp[],int mdlc,ArrayList<String> ala)throws IOException
	{
		if(mdi[0]==0)
		{
			String s=sc.next();
			if(sc.hasNext())
				s=s.substring(0,s.length()-1);//to remove carriage string in the end
			return s;
		}
		else
		{
			String s="";
			Scanner mdt=new Scanner(new File("MDT.txt")).useDelimiter("\n");
			mdtp[0]++;
			while(mdt.hasNext())
			{
				String k=mdt.next();
				k=k.substring(0,k.length()-2);
				Scanner s2=new Scanner(k).useDelimiter(" ");
				int number=Integer.parseInt(s2.next());
				if(number==mdtp[0])
				{
					while(s2.hasNext())
					{
						s=s+s2.next()+" ";
					}

				}
			}
			Scanner s1=new Scanner(s).useDelimiter(" ");
			s="";
			while(s1.hasNext())
			{
				String k=s1.next();
				if(k.charAt(0)=='#')
				{
					for(int i=0;i<ala.size();i++)
						if(Integer.parseInt(k.substring(1,k.length()))==i)
						{
							k=ala.get(i);
							break;
						}
				}
				s=s+k+" ";
			}
			s=s.substring(0,s.length()-1);
			if(s.equalsIgnoreCase("MEND"))
			{
				if(mdlc==0)
				{
					mdi[0]=0;
					s=sc.next();
					s=s.substring(0,s.length()-1);
				}
				ala.clear();
			}
			return s;
		}
	}
}