import java.io.*;
import java.util.*;
class macro1
{
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(new File("macrotest1.txt")).useDelimiter("\n");
    	PrintWriter temp=new PrintWriter(new BufferedWriter(new FileWriter("temp.txt")));
		PrintWriter op=new PrintWriter(new BufferedWriter(new FileWriter("Output.txt")));
		PrintWriter mdt=new PrintWriter(new BufferedWriter(new FileWriter("MDT.txt")));
		PrintWriter mnt=new PrintWriter(new BufferedWriter(new FileWriter("MNT.txt")));
		int mdtc=1,mntc=1;
		ArrayList<String> ala = new ArrayList<String>();
		//pass 1
		while(sc.hasNext())
		{
			String s=sc.next();
			s=s.substring(0,s.length()-1); //to remove carriage string in the end \r
			if(s.equalsIgnoreCase("MACRO"))
			{
				s=sc.next();
				s=s.substring(0,s.length()-1);
				Scanner s1=new Scanner(s);
				String mname=s1.next();
				mnt.println(mname+" "+mdtc);
				mntc+=1;
				String ars=s1.next();
				Scanner s2=new Scanner(ars).useDelimiter(",");
				while(s2.hasNext())
				{
					ala.add(s2.next());
				}
				mdt.println(mdtc+" "+s);
				mdtc+=1;
				do{
					s=sc.next();
					s=s.substring(0,s.length()-1);
					s2=new Scanner(s).useDelimiter(" |\\,");
					mdt.print(mdtc+" "+s2.next()+" ");
					int l=ala.size();
					while(s2.hasNext())
					{
						String ag=s2.next();
						for (int i=0;i<l ;i++ ) {
							if(ag.equalsIgnoreCase(ala.get(i)))
							{
								ag="#"+i;
								break;
							}
						}
						mdt.print(ag+",");
					}
					mdt.print('\n');
					mdtc+=1;
					if(s.equalsIgnoreCase("MEND"))
					{
						break;
					}

				}while(true);
			}
			else
			{
				temp.println(s);
				if(s.equalsIgnoreCase("END"))
				{
					break;
				}
			}
				ala.clear();
		}
		mdt.close();
		mnt.close();
		temp.close();
		Scanner temp1=new Scanner(new File("temp.txt")).useDelimiter("\n");
		int mdtp;
		//pass 2
		while(temp1.hasNext())
		{
			String s=temp1.next();
			s=s.substring(0,s.length()-1);
			Scanner s1=new Scanner(s).useDelimiter(" |\\,");
			String k=s1.next();
			mdtp=-1;
			Scanner mnt1=new Scanner(new File("MNT.txt")).useDelimiter("\n");
			while(mnt1.hasNext())
			{
				String str=mnt1.next();
				str=str.substring(0,str.length()-1);
				Scanner s2=new Scanner(str).useDelimiter(" ");
				if(k.equalsIgnoreCase(s2.next()))
				{
					mdtp=(Integer.parseInt(s2.next()));
					break;
				}
			}
			if(mdtp==-1)
			{
				op.println(s);
				if(s.equalsIgnoreCase("END"))
					break;
			}
			else
			{
				while(s1.hasNext())
				{
					ala.add(s1.next());
				}
				mdtp+=1;
				int n;
				String str;
				boolean flag=true;
				Scanner mdt1=new Scanner(new File("MDT.txt")).useDelimiter("\n");
			while(mdt1.hasNext()&&flag){

				do{
				str=mdt1.next();
				str=str.substring(0,str.length()-1);
				Scanner s2=new Scanner(str).useDelimiter(" |\\,");
				n=Integer.parseInt(s2.next());
				}while(n!=mdtp);

				Scanner s2=new Scanner(str).useDelimiter(" |\\,");
				Scanner s3=new Scanner(str).useDelimiter(" |\\,");
				s2.next();
				s3.next();
				String t1=s3.next();
				if(t1.equalsIgnoreCase("MEND"))
				{
					flag=false;
					break;
				}
				while (s2.hasNext()){
					String t=s2.next();
					if (t.charAt(0)=='#') {
						int l=Integer.parseInt(t.substring(1,t.length()));	
						t=(ala.get(l));
					}
					op.print(t+" ");
				}
				op.print('\n');
				mdtp++;
			}
		}

			ala.clear();
		}
		op.close();
	}
}