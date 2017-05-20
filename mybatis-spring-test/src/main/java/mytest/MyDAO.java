package mytest;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MyDAO
{
	String MQL_GET_ALL_CHECKSUMS = "select distinct checksum from test";
	String MQL_GET_CHECKSUM = "select checksum from test where file_path=#{filePath}";

	@Select(MQL_GET_ALL_CHECKSUMS)
	public Set<String> getChecksums();

	@Select(MQL_GET_CHECKSUM)
	public String getChecksum(@Param("filePath") String filePath) throws Exception;
}
