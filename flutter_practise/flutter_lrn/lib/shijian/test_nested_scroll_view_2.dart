
import 'package:flutter/material.dart';



class SnapAppBar extends StatelessWidget {
  const SnapAppBar({Key? key}) : super(key: key);

  
  Widget builderSilver(int count) {
    return SliverFixedExtentList(
      delegate: SliverChildBuilderDelegate((ctx,index){
        //创建列表项
        return Container(
          alignment: Alignment.center,
          color: Colors.lightBlue[100 * (index % 9)],
          child: Text('list item $index'),
        );},
          childCount: count
      ),
      itemExtent: 50,
    );
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // body: Text("1111"),
      body: NestedScrollView(
        headerSliverBuilder: (ctx,innerBoxIsScrolled){
          return <Widget>[
            SliverAppBar(
              floating: true,
              snap: true,
              expandedHeight: 200,
              forceElevated: innerBoxIsScrolled,
              flexibleSpace: FlexibleSpaceBar(
                background: Image.asset(
                  "pics/bg.jpeg",
                  fit: BoxFit.cover,
                ),
              ),
            ),
          ];
        },
        body: Builder(builder: (ctx){
          return CustomScrollView(
            slivers: [
              builderSilver(30)
            ],
          );
        }),
      ),
    );
  }


}