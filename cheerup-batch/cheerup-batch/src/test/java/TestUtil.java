import java.io.IOException;

import org.junit.Test;

import com.cheerup.batch.utils.ResourceReadUtil;

public class TestUtil {
	@Test
	public void test() throws IOException {
		String sql = ResourceReadUtil.getResource("/sql/insertFromStudyActLog.sql");
		System.out.println(sql);
	}
}
