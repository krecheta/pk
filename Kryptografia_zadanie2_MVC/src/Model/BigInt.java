package Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BigInt {
	final private int[] values;
        private boolean sign; // true dodadni false ujemny 
	
	public BigInt(String val) {
                this.sign = true; // domyslnie jest true
		values = new int[val.length()];		
		for(int i = 0; i< values.length; i++) {
			values[i] = Character.getNumericValue(val.charAt(i));
		}
	}
        
        // mozemy tworzyc lcizbe odrazu ze znakiem 
	public BigInt(String val, boolean sign) {
                this.sign = sign; // domyslnie jest true
		values = new int[val.length()];		
		for(int i = 0; i< values.length; i++) {
			values[i] = Character.getNumericValue(val.charAt(i));
		}
	}
	
	public int[] getValues() {
		return values;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(values).replaceAll("\\[|\\]|,|\\s", "");
	}
        
        //Mnozenie karatsuba
	/*public BigInt mul(BigInt num2){
        BigInt num1 = this;
        BigInt high1, low1, high2, low2, X, Y, Z;
        BigInt ten = new BigInt("10");
        BigInt houndred = new BigInt("10");
        BigInt eleven = new BigInt("11");
        BigInt zero = new BigInt("0");
        int m,n;
       
        boolean signValue = checkSignForMulAndDivOperation(num2);

        if (num1.isSmmaler(zero) || num2.isSmmaler(zero)) {
          return zero;
        }
        if (num1.isSmmaler(eleven) && num2.isSmmaler(eleven))
            return new BigInt(Long.toString(Long.valueOf(num1.toString()) * Long.valueOf(num2.toString())));
        
        
       // if (num1.isSmmaler(new BigInt(Long.toString(Long.MAX_VALUE))) && num2.isSmmaler(new BigInt(Long.toString(Long.MAX_VALUE)))){
        //    return new BigInt(Long.toString(Long.valueOf(num1.toString()) * Long.valueOf(num2.toString())));
        //}

        if (num1.toString().length() % 2 == 1) {
            num1 = new BigInt("0" + num1.toString());
        }
        
         if (num2.toString().length() % 2 == 1) {
            num2 = new BigInt("0" + num2.toString());
        }
         
        if (num1.toString().length() > num2.toString().length()) {
            m = num1.toString().length() - num2.toString().length();
            for (int i=0; i<m; i++)
            num2 = new BigInt( "0" + num2.toString());
        } 
        if (num2.toString().length() > num1.toString().length()) {
            m = num2.toString().length() - num1.toString().length();
            for (int i=0; i<m; i++)
            num1 = new BigInt( "0" + num1.toString());
        } 
        
        n = num1.toString().length() / 2;

        high1 = new BigInt(num1.toString().substring(0,n));
        low1 =  new BigInt(num1.toString().substring(n,num1.toString().length()));
        high2 = new BigInt(num2.toString().substring(0, n));
        low2 =  new BigInt(num2.toString().substring(n,num2.toString().length()));
        
        X = high1.mul(high2);
        Y = low1.mul(low2);
        Z = ((high1.add(low1)).mul((high2.add(low2)))).sub(X).sub(Y);
        
        for(int i=0; i<(n*2); i++)
             X = new BigInt(X.toString()+"0");
          for(int i=0; i<n; i++)
             Z = new BigInt(Z.toString()+"0");
        
        BigInt result = new BigInt(X.add(Z.add(Y)).toString(), signValue);
        boolean flag = true;
        while(flag){
            if (result.toString().substring(0, 1).equals("0") && result.toString().length() > 1)
                result = new BigInt(result.toString().substring(1,result.toString().length()));
            else
                flag = false;
        }
      return result;
     }*/
	//dodawanie
	public BigInt add(BigInt other) {
             boolean signValue;
              BigInt result;
            if((this.sign && other.getSign()) || (!this.sign && !other.getSign()))
                 signValue = this.sign;
           
            else if(this.sign && !other.getSign() ){ // +- 3+-5
             if (this.isBigger(other)){
                 BigInt other2 = new BigInt(other.toString(),!other.getSign());                 
                 return this.sub(other2);
             }
               
             else
                 result = new BigInt(other.toString(),!other.getSign());
                 BigInt other2 = new BigInt(this.toString(),this.getSign());
                 result = result.sub(other2);
                 result.setSign(other.getSign());
                 return result;
               }
            
            else{//-+
               if (this.isBigger(other)){ 
                    result = new BigInt(this.toString(),!this.sign);
                    BigInt other2 = new BigInt(other.toString());
                    result = result.sub(other2);
                    result.setSign(this.sign);
                    return result;
                }
                else{
                    BigInt other2 = new BigInt(this.toString(),!this.sign);
                    result = other.sub(other2);
                    result.setSign(other.getSign());
                    return result;
                }
            }
                
		int[] val1 = values;
		int[] val2 = other.getValues();
		
		//druga tablica musi by� wi�ksza
		if(val1.length > val2.length) {
			int[] temp = val1;
			val1 = val2;
			val2 = temp;
		}
		
		//r�nica rozmiaru tablic
		int dif = val2.length - val1.length;
		
		//zmienne pomocnicze przy dodawaniu
		int sum = 0, carry = 0;
		StringBuilder s = new StringBuilder();
		
		//zaczynaj�c od ko�ca przechodzimy przez tablic� 
		//do rozmiaru mniejszej tablicy i wykonujemy "szkolne" dodawanie
		for(int i = val1.length-1; i >= 0; i--) {
			//dodajemy odpowiadaj�ce sobie elementy i "przeniesienie"
			sum = val1[i] + val2[i+dif] + carry;
			//doklejamy jedno�ci
			s.append(sum%10);
			//zapisujemy przeniesienie (je�eli suma b�dzie wi�ksza od 10)
			carry = sum/10;
		}
		
		//doklejamy liczby z drugiej, wi�kszej tablicy
		for(int i = dif-1; i >= 0; i--) {
			sum = val2[i] + carry;
			s.append(sum%10);
			carry = sum/10;
		}
		
		//doklejamy przeniesienie z ostatniego obiegu p�tli
		if(carry != 0) {
			s.append(carry);
		}
		
		//odwracamy stringa i zwracamy BigInt
		return new BigInt(s.reverse().toString(),signValue);
	}
	
	//odejmowanie
	public BigInt sub(BigInt other) {
            boolean signValue = this.sign;
            boolean flag = false;
            BigInt other2; 
           
            if(this.sign && other.getSign()){ // ++
                if (this.isBigger(other))
                  ;        
                else{
                    BigInt result = other.sub(this);
                    result.setSign(false);
                    return result;
                }
            }
            else if(!this.sign && !other.getSign()){ //-- (-7 --5)
                 if (this.isBigger(other)){
                      other2 = new BigInt(other.toString());
                      other2.setSign(!other.getSign());
                      return this.add(other2);
                 }
                 else{
                       other2 = new BigInt(other.toString());
                      other2.setSign(!other.getSign());
                      return other2.add(this);
                }
            }
            else if (this.sign && !other.getSign()){ 
                  
                 other2 = new BigInt(other.toString());
                 other2.setSign(!other.getSign());
                  other2 = this.add(other2);
                  return other2;
                 }
            else{ //-+ -1-5
                 other2 = new BigInt(this.toString());
                 other2.setSign(!this.getSign());
                 BigInt result = other2.add(other);
                 result.setSign(this.sign);
                 return result;
            }
                int[] val1,val2;
                if(flag){
                     val2 = values;
                     val1 = other.getValues();
                }
                else{
                    val1 = values;
                    val2 = other.getValues();
                }
		//zmienne pomocnicze
		int sub = 0, carry = 0, dif = val1.length - val2.length;
		StringBuilder s = new StringBuilder();
		
		//iteruj�c od ko�ca do rozmiaru kr�tszej tablicy odejmujemy od siebie odpowiadaj�ce liczby
		for(int i=val2.length-1; i >= 0; i--) {
			sub = val1[i+dif] - val2[i] - carry;
			
			//je�eli r�nica jest ujemna to robimy przeniesienie
			if(sub < 0) {
				sub += 10;
				carry = 1;
                                
			} 
                        else {
				carry = 0;
			}
			s.append(sub);
		}
		
		//doklejamy pozosta�e liczby z wi�kszej tablicy
		for(int i = dif-1; i >=0; i--) {
			
			if(val1[i] == 0 && carry == 1) {
				s.append(9);
				continue;
			}
			
			sub = val1[i] - carry;
			
			if(i > 0 || sub > 0) {
				s.append(sub);
			}
                        carry=0;
		}
		return new BigInt(cutZeros(s.reverse().toString()),signValue);
	}
	
	//mno�enie
	public BigInt mul(BigInt other) {
                boolean signValue = checkSignForMulAndDivOperation(other);
        	int[] val1 = values;
		int[] val2 = other.getValues();
		
		if(val1.length == 1 && val1[0] == 0) {
			return new BigInt("0");
		}
		
		if(val2.length == 1 && val2[0] == 0) {
			return new BigInt("0");
		}
		
		StringBuilder product;
        int result, carry;
        List<String> results = new ArrayList<>();

        for(int i = 0; i < val1.length; i++) {
        	product = new StringBuilder();
            carry = 0;

            for(int j = 0; j < i; j++) {
                product.append(0);
            }

            for(int j = 0; j < val2.length; j++) {
            	result = val1[val1.length - 1 - i] * val2[val2.length - 1 - j] + carry;
                carry = result / 10;
                product.insert(0, Integer.toString(result % 10, 10));
            }

            if(carry > 0) {
                product.insert(0, Integer.toString(carry, 10));
            }

            results.add(product.toString());
        }

        product = new StringBuilder();

        for(int i = 0; i < results.size(); i++) {
            product = new StringBuilder(add(results.get(i), product.toString()));
        }
        return new BigInt(product.toString(),signValue);
	}
	
	//dzielenie (je�eli modulo == true to zwracamy reszt� z dzielenia)
	public BigInt div(BigInt other, boolean modulo) {
        	checkSignForMulAndDivOperation(other);
                String val1 = this.toString();
		String val2 = other.toString();
                if(modulo)
                if (modulo && (!this.getSign()))
                    return moduloFromNegativeNumber(this, other);
		
		if(!isSmallerOrEqual(val1, val2)) {
			if(modulo) {
				return this;
			} else {
				return new BigInt("0");
			}
		}
		
		StringBuilder quotient = new StringBuilder();
        String remainder = "";
        int result = 0, index = 0;

        for(int i = 1; i < val1.length() + 1; i++) {
            if(isSmallerOrEqual(val1.substring(0, i), val2)) {
                remainder = val1.substring(0, i);
                index = i;
                break;
            }
        }

        do{
        	for(int i = 0; i < 10; i++) {
                if (isSmallerOrEqual(remainder, mul(val2, Integer.toString(i, 10)))) {
                    result = i;
                } else {
                    break;
                }
            }

            remainder = sub(remainder, mul(val2, Integer.toString(result, 10)));

            if (index < val1.length()) {
                remainder += val1.charAt(index);
                remainder = cutZeros(remainder);
            }

            quotient.append(Integer.toString(result, 10));
            index++;
        } while (index != val1.length() + 1);

        if(modulo) {
            return new BigInt(remainder);
        }
        return new BigInt(quotient.toString(),sign);
	}
	
        
    
        
        public BigInt pow2(BigInt other){
         BigInt zero= new BigInt("0");
         BigInt one = new BigInt("1");
         BigInt two = new BigInt("2");
        boolean signValue = true;
        
        if (!(other.div(two,true).equals(zero)) &&(!this.sign))
                    signValue = false;
        
        if (other.equals(zero))
            return new BigInt("1",signValue);
        
        if (!other.div(two, true).equals(zero))
            return new BigInt(this.mul(
                    this.pow((other.sub(one)).div(two,false)))
                                .toString(), signValue);
        BigInt a = new BigInt(this.pow(other.div(two,false)).toString(), signValue);
        return a.mul(a);
   
        }
        
        
        
	//potegowanie
	public BigInt pow(BigInt other) {
            System.err.println("Potwgowanie x^y " + this.toString() + " " + other.toString());
		String val = this.toString();
		String index = other.toString();
		String result = "1";
		boolean sign = true;
                if (!(other.div(new BigInt("2"),true).equals(new BigInt("0"))) &&(!this.sign))
                    sign = false;
		while(!index.equals("0")) {
			result = mul(result, val);
			index = sub(index, "1");
		}
		
		return new BigInt(result);
	}
	
	//mno�enie dw�ch string�w (potrzebne do dzielenia dw�ch BigInt�w)
	public String mul(String val1, String val2) {
		if(val1.length() == 1 && Character.getNumericValue(val1.charAt(0)) == 0) {
			return "0";
		}
		
		if(val2.length() == 1 && Character.getNumericValue(val2.charAt(0)) == 0) {
			return "0";
		}
		
		StringBuilder product;
        int result, carry;
        List<String> results = new ArrayList<>();

        for(int i = 0; i < val1.length(); i++) {
        	product = new StringBuilder();
            carry = 0;

            for(int j = 0; j < i; j++) {
                product.append(0);
            }

            for(int j = 0; j < val2.length(); j++) {
            	result = Character.getNumericValue(val1.charAt(val1.length() - 1 - i)) * Character.getNumericValue(val2.charAt(val2.length() - 1 - j)) + carry;
                carry = result / 10;
                product.insert(0, Integer.toString(result % 10, 10));
            }

            if(carry > 0) {
                product.insert(0, Integer.toString(carry, 10));
            }

            results.add(product.toString());
        }

        product = new StringBuilder();

        for(int i = 0; i < results.size(); i++) {
            product = new StringBuilder(add(results.get(i), product.toString()));
        }
        
        return product.toString();
	}
	
	//dodawanie dw�ch string�w (potrzebne przy mno�eniu BigInt�w)
	public String add(String val1, String val2) {

		if(val1.length() > val2.length()) {
			String temp = val1;
			val1 = val2;
			val2 = temp;
		}

		int dif = val2.length() - val1.length();
		
		int sum = 0, carry = 0;
		StringBuilder s = new StringBuilder();
		
		for(int i = val1.length()-1; i >= 0; i--) {		
			sum = Character.getNumericValue(val1.charAt(i)) + Character.getNumericValue(val2.charAt(i+dif)) + carry;
			s.append(sum%10);
			carry = sum/10;
		}
		
		for(int i = dif-1; i >= 0; i--) {
			sum = Character.getNumericValue(val2.charAt(i)) + carry;
			s.append(sum%10);
			carry = sum/10;
		}

		if(carry != 0) {
			s.append(carry);
		}

		return s.reverse().toString();
	}
	
	//odejmowanie dw�ch string�w (potrzebne przy pot�gowaniu BigInt�w)
	public String sub(String val1, String val2) {
		
		int sub = 0, carry = 0, dif = val1.length() - val2.length();
		StringBuilder s = new StringBuilder();

		for(int i=val2.length()-1; i >= 0; i--) {

			sub = Character.getNumericValue(val1.charAt(i+dif)) - Character.getNumericValue(val2.charAt(i)) - carry;

			if(sub < 0) {
				sub += 10;
				carry = 1;
			} else {
				carry = 0;
			}
			s.append(sub);
		}
		
		for(int i = dif-1; i >=0; i--) {
			
			if(Character.getNumericValue(val1.charAt(i)) == 0 && carry == 1) {
				s.append(9);
				continue;
			}
			
			sub = Character.getNumericValue(val1.charAt(i)) - carry;
			
			if(i > 0 || sub > 0) {
				s.append(sub);
			}
			carry = 0;
		}
		return cutZeros(s.reverse().toString());
	}
	
	//obcina zera z lewej strony
	private String cutZeros(String str) {
		int i;
		for(i = 0; i < str.length(); i++) {
			if(Character.getNumericValue(str.charAt(i)) != 0) {
				break;
			}
		}
		
		if(str.substring(i).length() == 0) {
			return "0";
		} else {
			return str.substring(i);
		}
	}

	//zwraca true je�li pierwsza liczba jest wi�ksza
	public boolean isSmallerOrEqual(String val1, String val2) {
		
		if(val1.equals(val2)) {
			return true;
		}
		
		if(val2.length() < val1.length()) {
			return true;
		} else if (val1.length() == val2.length()) {
			for(int i=0; i<val1.length(); i++) {
				if(Character.getNumericValue(val2.charAt(i)) < Character.getNumericValue(val1.charAt(i))){
					return true;
				}
				if(Character.getNumericValue(val2.charAt(i)) > Character.getNumericValue(val1.charAt(i))){
					return false;
				} 
			}
		}
		
		return false;
	}

        public BigInt moduloFromNegativeNumber(BigInt a, BigInt b){
            return a.sub((a.div(b,false)).sub(new BigInt("1")).mul(b));
        }
   public boolean equals(BigInt x){
       return this.toString().equals(x.toString());
   }
   public boolean notequals(BigInt x){
       return !this.toString().equals(x.toString());
   }
   
   public boolean isSmmaler(BigInt x){
       String val1 = this.toString();
       String val2 = x.toString();
       int a1,a2;
        
       if(val1.length()<val2.length() )
                    return true;
       
       else if ( val1.length() == val2.length()){
            for(int i=0 ;i<val1.length(); i++){
                   a1 = Integer.parseInt(val1.substring(i,i+1));
                   a2 = Integer.parseInt(val2.substring(i,i+1));
                  if (a1 < a2)
                      return true;
                  else if (a1 > a2)
                          return false;
                  else
                    continue;
                 }
                }
        return false;      
   }
   
    public boolean isBigger(BigInt x){
        return !isSmmaler(x);    
    }
    
   public boolean getSign(){
       return this.sign;
   }
   
   private void  setSign(boolean sign){
       this.sign = sign;
   }
   
    private boolean checkSignForMulAndDivOperation( BigInt b){
        if(this.sign && b.getSign())
            return this.sign;
        else if(!this.sign && !b.getSign())// jezeli ten znak jest ujemny 
            return true;
        else
            return false;
    }


}
