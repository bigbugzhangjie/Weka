package jack.weka;

import java.io.File;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Instances ins = null;
		Classifier cfs = null;

		try {

			// ����ѵ����������
			File file = new File("E:/workspace/Weka/data/contact-lenses.arff");
			ArffLoader loader = new ArffLoader();
			loader.setFile(file);
			ins = loader.getDataSet();
			ins.setClassIndex(ins.numAttributes() - 1);

			// ��ʼ��������
			cfs = (Classifier) Class.forName(
					"weka.classifiers.bayes.NaiveBayes").newInstance();

			// ʹ��ѵ���������з���
			cfs.buildClassifier(ins);

			// ʹ�ò����������Է�������ѧϰЧ��
			Instance testInst;
			Evaluation testingEvaluation = new Evaluation(ins);
			int length = ins.numInstances();
			for (int i = 0; i < length; i++) {
				testInst = ins.instance(i);
				testingEvaluation.evaluateModelOnceAndRecordPrediction(cfs,
						testInst);
			}

			// ��ӡ������
			System.out.println("�������ȷ��" + (1 - testingEvaluation.errorRate()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}