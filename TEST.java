import java.io.*;
import java.math.BigInteger;
import java.util.*;
class TEST
{
	private ArrayList<BigInteger> sequence = new ArrayList<>();
	private static BigInteger MOD = new BigInteger("1000000007");
	private static BigInteger VAL = new BigInteger("45");

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		TEST testOb = new TEST();
		System.out.println("Enter the value of N");
		int n = Integer.parseInt(input.readLine());
		testOb.generateSequence(n);
	//	System.out.println(testOb.getAnswer());
	}

	private BigInteger getAnswer() {
		BigInteger finalScore = new BigInteger("0");
		int last = sequence.size() - 1;
		while(last >= 0) {
			// player 1 makes a move
			if(getScore(sequence, 0).compareTo(getScore(sequence, last)) > 0) {
				finalScore = finalScore.add(sequence.get(0));
				sequence.remove(0);
			} else {
				finalScore = finalScore.add(sequence.get(last));
				sequence.remove(last);
			}
			
			// the list size is reduced by 1
			last--;

			// if the sequence has not ended then player 2 makes a move
			if(last >= 0) {
				if(getScore(sequence,0).compareTo(getScore(sequence,last)) > 0) {
					sequence.remove(0);
				} else {
					sequence.remove(last);
				}
				// the list size is further reduced by 1
				last--;	
			}
		}
		return finalScore;
	}


	private BigInteger getScore(ArrayList<BigInteger> seq1, int pos) {
		ArrayList<BigInteger> seq = new ArrayList<>(seq1);
		BigInteger score = new BigInteger("0");
		score = score.add(seq.get(pos));
		seq.remove(pos);
		int move;
		while(seq.size() > 0) {
			move = getMove((seq));
			score = score.subtract(seq.get(move));
			seq.remove(move);
			if(seq.size() > 0) {
				move = getMove(seq);
				score = score.add(seq.get(move));
				seq.remove(move);
			}
		}
		return score;
	}
	
	private int getMove(ArrayList<BigInteger> sequence) {
		if(sequence.get(0).compareTo(sequence.get(sequence.size() - 1)) > 0) {
			return 0;
		} else {
			return sequence.size() - 1;
		}
	}

	private void generateSequence(int num) {
		// add 0 to the sequence
		sequence.add(new BigInteger("0"));

		// generate the rest of the list
		for (int i = 1; i < num; i = i + 1) {
			sequence.add(sequence.get(i - 1).pow(2).add(VAL).mod(MOD));
		}

		BigInteger number = new BigInteger("0");
		for (int i = 0; i < num; i = i + 2 ) {
			number = number.add(sequence.get(i)); 
		}
		System.out.println(number);
	}
}