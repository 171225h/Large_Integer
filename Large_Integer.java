package week4;

public class Large_Integer{

	protected int size;
	protected int[] num;
	protected int sign;
	
	public Large_Integer() {
		this("0");
	}

	public Large_Integer(String n) {
		this.size = 10;
		this.num = new int[size];

		this.sign = n.charAt(0)=='-' ? 1:0;
		
		if(size<=n.length())
			arrSizeUp(n.length());

		this.num = copyString(n);
	}

	/**
	 * �迭�� ũ�⸦ �÷��ִ� �޼ҵ�
	 * @param nLength ���� size�� ���� nLength
	 */
	protected void arrSizeUp(int nLength) {

		while(this.size <= nLength)
			this.size *= 2;
		num=copyArray();
	}

	/**
	 * �⺻���� 0�� num[]�� ����
	 * @return num[]
	 */
	protected int[] copyArray() {
		int[] result = new int[size];
		return copyArray(num, 0);
	}

	/**
	 * arr[]�� ���� ���� num[]�� ������.
	 * @param arr ������ �迭
	 * @param sign ��ȣ.
	 * @return num[]
	 */
	protected int[] copyArray(int[] arr, int sign) {
		int[] result = new int[size];

		result[0] = sign;

		int start = result.length-arr.length;

		for(int i = result.length-1, j=1; i>=result.length-arr.length+1; i--, j++)
			result[i]=arr[arr.length-j];

		return result;
	}
	
	/**
	 * ���� ���� num[]�� ����.
	 * @param n ���� ��
	 * @return num[]
	 */
	protected int[] copyString(String n) {

		this.sign = n.charAt(0)=='-' ? 1 : 0;
		int start = sign==1 ? 1 : 0;
		int[] result = new int[size];

		result[0]=sign;
		int resultPoint = size - n.length();

		for(int i = start; i<n.length(); i++) {
			result[resultPoint+i]=Integer.parseInt(Character.toString(n.charAt(i)));
		}

		return result;
	}

	/**
	 * �迭�� �� ���� ����ϴ� �޼ҵ�.
	 * @return �迭�� ����ִ� ��
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int start=1;


		for(int i = 1; i<=size; i++) {
			if(i==size) {
				start=size-1;
			}
			else if(num[i]!=0) {
				start=i;
				break;
			}
		}
		
		if(start==size)
			return "0";

		for(int i = start; i<size; i++)
			sb.append(num[i]);

		if(num[0]==0)
			return sb.toString();
		else
			return "-"+sb.toString();


	}

	public int whitIsSign() {
		return this.sign;
	}
	/**
	 * u*(10^m)�� �ϴ� �޼ҵ��Դϴ�.
	 * @param n m�� ��
	 * @return num�� �ִ� ���� 10�� m���� �� �� ���� ����
	 */
	public String nPower(int n) {
		if(n==0)
			return toString();
		else if(n<0)
			return minusNPower(Math.abs(n));
		else {

			int start=1;
			for(int i = 1; i<size; i++) {
				if(num[i]!=0) {
					start=i;
					break;
				}
			}
			int nLength = size-start;
			if(nLength+n>=size) {
				arrSizeUp(nLength+n);
				for(int i = 1; i<size; i++) {
					if(num[i]!=0) {
						start=i;
						break;
					}
				}
			}

			for(int i = start; i<size; i++) {
				num[i-n]=num[i];
				num[i]=0;
			}


			return toString();
		}
	}

	/**
	 * u/(10^m)�� �ϴ� �޼ҵ��Դϴ�.
	 * Integer���̱� ������, �������� �����մϴ�.
	 * @param n m
	 * @return u/(10^m)
	 */
	public String minusNPower(int n) {
		if(n<=0)
			return toString();
		
		int start=1;
		for(int i = 1; i<size; i++) {
			if(num[i]!=0) {
				start=i;
				break;
			}
		}

		int nLength = size-start;

		for(int i = 0; i<=nLength; i++) {
			num[size-1-i]=num[size-1-i-n];
			num[size-1-n-i]=0;
		}


		return toString();
	}
	
	public String remainder(int n) {
		if(n<=0)
			return toString();
		
		int start=1;
		for(int i = 1; i<size; i++) {
			if(num[i]!=0) {
				start=i;
				break;
			}
		}
		
		for(int i = size-1-n; i>=start; i--) {
			num[i]=0;
		}

		return toString();
	}
}