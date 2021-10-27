
import 'dart:isolate';

import 'package:flutter_lrn/practise/first_learn.dart';

void main() {
  testIsolate22();
}

void testIsolate22() {
  start22();
}

//当前函数在父Isolate中
Future<dynamic> asyncFactoriali(n) async {
  //父Isolate对应的ReceivePort对象
  final response = ReceivePort();
  //创建一个子Isolate对象
  await Isolate.spawn(_isolate, response.sendPort);
  final sendPort = await response.first as SendPort;
  final answer = ReceivePort();
  //给子Isolate发送数据
  sendPort.send([n, answer.sendPort]);
  return answer.first;
}

//子Isolate的入口函数，可以在该函数中做耗时操作
//_isolate必须是顶级函数（不能存在任何类中）或者是静态函数（可以存在类中）
_isolate(SendPort initialReplyTo) async {
  //子Isolate对应的ReceivePort对象
  final port = ReceivePort();
  initialReplyTo.send(port.sendPort);
  final message = await port.first as List;
  final data = message[0] as int;
  final send = message[1] as SendPort;
  //给父Isolate的返回数据
  send.send(syncFactorial(data));
}
syncFactorial(data)  {

  return data * 8;
}


//运行代码
start22() async {
  print("计算结果：${await asyncFactoriali(4)}");
}



void testHanShu() {

}
Isolate? isolate;
//子Isolate对象的入口函数，可以在该函数中做耗时操作
getMsg(sendPort) => sendPort.send("hello");

ttt(mmm) {
  mmm.sdjfs("dsfjks");
}

hello(tt) {
  tt.sd();
}

start() async {
  ReceivePort receivePort = ReceivePort();
  //创建子Isolate对象
  isolate = await Isolate.spawn(ttt, receivePort.sendPort);
  //监听子Isolate的返回数据
  receivePort.listen((data) {
    myPrint('data：$data');
    receivePort.close();
    //关闭Isolate对象
    isolate?.kill(priority: Isolate.immediate);
    isolate = null;
  });
}
void testIsolate() {
  start();
}




