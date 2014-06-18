import java.util.Arrays;

public class SortBinary {

	void sort(int arr[]) {
		int i = 0, j = arr.length - 1;
		do {
			while (i < arr.length && arr[i] == 0) {
				++i;
			}
			while (j < arr.length && arr[j] == 1) {
				--j;
			}
			if (i < j) {
				int c = arr[i];
				arr[i] = arr[j];
				arr[j] = c;
			} else {
				break;
			}
			++i;
			--j;
		} while (i < j && i < arr.length && j < arr.length);
	}

	public static void main(String[] args) {
		SortBinary sb = new SortBinary();
		int[] arr1 = new int[] { 0, 1 };
		sb.sort(arr1);
		System.out.println(Arrays.toString(arr1));
		arr1 = new int[] { 0, 0, 0 };
		sb.sort(arr1);
		System.out.println(Arrays.toString(arr1));
		arr1 = new int[] { 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 };
		sb.sort(arr1);
		System.out.println(Arrays.toString(arr1));
		arr1 = new int[] { 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 };
		sb.sort(arr1);
		System.out.println(Arrays.toString(arr1));
	}
}
