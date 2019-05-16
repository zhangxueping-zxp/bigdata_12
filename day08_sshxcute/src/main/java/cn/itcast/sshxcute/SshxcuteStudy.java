package cn.itcast.sshxcute;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.core.SSHExecUtil;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class SshxcuteStudy {

    public static void main(String[] args) throws TaskExecFailException {
        ConnBean connBean = new ConnBean("node03","root","123456");

        SSHExec instance = SSHExec.getInstance(connBean);
        //连接我们的linux服务器
        instance.connect();
        ExecCommand execCommand = new ExecCommand("echo 'helloworld' >>/export/servers/abchello.txt");

        Result exec = instance.exec(execCommand);

        instance.disconnect();
    }

}
