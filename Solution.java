import java.util.*;

public class Solution{
	private static List<Integer> _getPrimes(int upperLimit,List<Integer> knownPrimes){
		List<Integer> result = new LinkedList<>();
		if(knownPrimes.get(knownPrimes.size()-1)>upperLimit){
			for(int num:knownPrimes){
				if(num>upperLimit){
					break;
				}
				result.add(num);
			}
			return result;
		}
		
		boolean[] isNotPrime = new boolean[upperLimit+1];
		int num=2;
		for(int limit:knownPrimes){
			num = limit+limit;
			while(num<=upperLimit){
				isNotPrime[num] = true;
				num+=limit;
			}
		}
		for(int limit=num;limit<upperLimit;limit++){
			num = limit+limit;
			while(num<=upperLimit){
				isNotPrime[num] = true;
				num+=limit;
			}
		}
		for(int index=2;index<isNotPrime.length;index++){
			if(!isNotPrime[index]){
				result.add(index);
			}
		}
		knownPrimes = result;
		return result;
	}

	private static List<Integer> getPrimes(int lowerLimit,int upperLimit,List<Integer> knownPrimes){
		if(lowerLimit==0||lowerLimit==1)lowerLimit=2;
		int root = ((int) Math.sqrt(upperLimit)) + 1;
		List<Integer> result = new LinkedList<>();
		boolean[] isNotPrime = new boolean[upperLimit-lowerLimit+1];
		for(int prime : _getPrimes(root,knownPrimes)){
			int num = (lowerLimit / prime) * prime;
			if(num<lowerLimit) num+=prime;
			while(num<=upperLimit){
				isNotPrime[num-lowerLimit] = true;
				num+=prime;
			}
		}
		
		for(int index=0;index<isNotPrime.length;index++){
			if(!isNotPrime[index]){
				result.add(lowerLimit+index);
			}
		}
		return result;
	}
	
	
	public static void main(String args[]){
		List<Integer> knownPrimes = new LinkedList<Integer>();
		knownPrimes.add(2);
		knownPrimes.add(3);
		knownPrimes.add(5);
		knownPrimes.add(7);
		knownPrimes.add(11);
		knownPrimes.add(13);
		Scanner scanner = new Scanner(System.in);
		
		int tests = scanner.nextInt();
		for(int test=0;test<tests;test++){
			for(int num : getPrimes(scanner.nextInt(),scanner.nextInt(),knownPrimes)){
				System.out.println(num);
			}
			System.out.println("");
		}
		
	}
}