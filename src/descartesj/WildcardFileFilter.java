package descartesj;

import java.io.*;
import java.util.regex.*;
 
public class WildcardFileFilter implements FileFilter
{
    private String _pattern;
 
    public WildcardFileFilter(String pattern)
    {
        _pattern = pattern.replace("*", ".*").replace("?", ".");
    }
 
    @Override
    public boolean accept(File file)
    {
	return Pattern.compile(_pattern).matcher(file.getName()).find();
    }
}