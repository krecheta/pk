package Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BigInt {
	final private int[] values;
	
	public BigInt(String val) {
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
	
	//dodawanie
	public BigInt add(BigInt other) {
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
		return new BigInt(s.reverse().toString());
	}
	
	//odejmowanie
	public BigInt sub(BigInt other) {
		int[] val1 = values;
		int[] val2 = other.getValues();
		
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
		}
		          System.out.println("Model.BigInt.sub()" + cutZeros(s.reverse().toString()));
		return new BigInt(cutZeros(s.reverse().toString()));
	}
	
	//mno�enie
	public BigInt mul(BigInt other) {

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
        
        return new BigInt(product.toString());
	}
	
	//dzielenie (je�eli modulo == true to zwracamy reszt� z dzielenia)
	public BigInt div(BigInt other, boolean modulo) {
            System.out.println("Model.BigInt.div() dzielenie " + this.toString() +" przez " + other.toString());
		String val1 = this.toString();
		String val2 = other.toString();
		
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
        return new BigInt(quotient.toString());
	}
	
	//potegowanie
	public BigInt pow(BigInt other) {
            System.out.println("Model.BigInt.pow()" + this.toString() + " do potegi "+ other.toString()  );
		String val = this.toString();
		String index = other.toString();
		String result = "1";
		
		while(!index.equals("0")) {
			result = mul(result, val);
			index = sub(index, "1");
                        System.out.println("index " + index);
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

   public boolean equals(BigInt x){
       System.out.println("Model.BigInt.equals() porownanie " + this.toString() + " " +  x.toString());
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


}
