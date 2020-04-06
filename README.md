### okhttp简易封装，提供给刚入门的小师弟参考
### 本人菜鸟是一做后端的,兴趣驱使折腾了半天搞出来的
### 主要是对json格式数据和非json格式数据的一些处理
### 后端接口用的 php tp5
    /**
     * 获取一个非正常json的返回值
     * @return string
     */
    public function geterror()
    {
        return "这是一个错误的返回值";
    }

    /**
     * 获取去一个正常的json返回值
     */
    public function getsuccess()
    {
        // 获取到的参数原样返回
        $data = input();
        $data['type'] = $this->request->method();
        $this->success("请求成功",$data);
    }