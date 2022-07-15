package pjrb.cmm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
public class PjrbCryptoAlgorithmCreate {
	private static final Logger LOGGER = LoggerFactory.getLogger(PjrbCryptoAlgorithmCreate.class);
	 
	//계정암호화키 키
	public String algorithmKey = "pjrb";
 
	//계정암호화 알고리즘(MD5, SHA-1, SHA-256)
	public String algorithm = "SHA-256";
 
	//계정암호화키 블럭사이즈
	public int algorithmBlockSize = 1024;
 
	public static void main(String[] args) {
		PjrbCryptoAlgorithmCreate pjrbCrypto = new PjrbCryptoAlgorithmCreate();
 
		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		egovPasswordEncoder.setAlgorithm(pjrbCrypto.algorithm);
		System.out.println("------------------------------------------------------");
		System.out.println("알고리즘(algorithm) : "+pjrbCrypto.algorithm);
		System.out.println("알고리즘 키(algorithmKey) : "+pjrbCrypto.algorithmKey);
		System.out.println("알고리즘 키 Hash(algorithmKeyHash) : "+egovPasswordEncoder.encryptPassword(pjrbCrypto.algorithmKey));
		System.out.println("알고리즘 블럭사이즈(algorithmBlockSize)  :"+pjrbCrypto.algorithmBlockSize);
 
	}
}
