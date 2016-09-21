### Goal ###
//The goal of this challenge was to come up with a game in 1,000 characters or less.
//My game was a small dungeon crawler, called Gretel.

### Revision 0.1 ###
// This part was killing my character count -- at this stage, was around 1160 characters with whitespace removed.
// Each index counts as an opening (0=no, 1=yes) to another connected room
        R[][] s = new R[5][5];
        s[0][0]=new R(0,1,0,0);
        s[0][1]=new R(0,0,0,1);
        s[0][2]=new R(0,0,1,1);
        s[0][3]=new R(0,1,1,1);
        s[0][4]=new R(0,1,1,0);
        s[1][0]=new R(1,1,0,0);
        s[1][1]=new R(0,1,0,1);
        s[1][2]=new R(0,1,1,0);
        s[1][3]=new R(1,0,0,0);
        s[1][4]=new R(1,1,0,0);
        s[2][0]=new R(1,0,0,1);
        s[2][1]=new R(1,1,1,0);
        s[2][2]=new R(1,0,0,1);
        s[2][3]=new R(0,0,1,1);
        s[2][4]=new R(1,1,1,0);
        s[3][0]=new R(0,1,0,1);
        s[3][1]=new R(1,0,1,1);
        s[3][2]=new R(0,0,1,1);
        s[3][3]=new R(0,1,1,0);
        s[3][4]=new R(1,0,0,0);
        s[4][0]=new R(1,0,0,1);
        s[4][1]=new R(0,0,1,1);
        s[4][2]=new R(0,0,1,0);
        s[4][3]=new R(1,0,0,0);
        
### Revision 0.2 ###
// So I changed my Room() constructor from:
public Room(int w,int x,int y,int z){u=w;d=x;l=y;r=z;}
// To:
public R(int x) {
        u=(x>>3)&1;
        d=(x>>2)&1;
        l=(x>>1)&1;
        r=(x>>0)&1;
    }
// So now I could instead set my door values based on bits!
        R[][] s = new R[5][5];
        s[0][0]=new R(4);
        s[0][1]=new R(1);
        s[0][2]=new R(3);
        s[0][3]=new R(7);
        s[0][4]=new R(6);
        s[1][0]=new R(14);
        s[1][1]=new R(7);
        s[1][2]=new R(6);
        s[1][3]=new R(8);
        s[1][4]=new R(14);
        s[2][0]=new R(9);
        s[2][1]=new R(15);
        s[2][2]=new R(9);
        s[2][3]=new R(3);
        s[2][4]=new R(15);
        s[3][0]=new R(5);
        s[3][1]=new R(11);
        s[3][2]=new R(3);
        s[3][3]=new R(6);
        s[3][4]=new R(8);
        s[4][0]=new R(9);
        s[4][1]=new R(3);
        s[4][2]=new R(2);
        s[4][3]=new R(8);
// This change saved me about 150 characters after removing whitespace!


### Revision 0.3 ###
// At this stage, I'm still over my 1000 max and still a bit of work to do.
// Idea!
int[] r = {4,1,3,7,6,14,7,6,8,14,9,15,9,3,15,5,11,3,6,8,9,3,2,8,0};
R[][] s = new R[5][5];
int v=0;
for(int i=0;i<5;i++)
    for(int k=0;k<5;k++) {
        s[i][k] = new R(r[v]);
        v++;
    }
// This change saved a whopping ~250 characters - yeesh!
// Current character count after removing whitespace: ~750

### Revision 0.4 ###
### Final (w/comments) ###
import java.util.*;
public class G {
    static void p(Object l){System.out.print(l);}
    public static void main(String[]b){
        /*
        x,y:    current position in maze
        v:      global iterator for r[]
        c:      starting crumbs
         */
        int x=2,y=2,v=0,c=5;

        // fancy colors:  R{eset}, G{reen}
        String R="\u001B[0m";
        String G="\u001B[32m";

        // bit-representations of rooms
        // up:8, down:4, left:2, right:1
        // 13 == 1101 == u:d:r (no left)
        int[] r = {
                4,  1,  3,  7,  6 , 0,
                12, 5,  6,  8,  13, 6,
                9,  14, 9,  3,  15, 12,
                5,  11, 3,  6,  8,  8,
                5,  7,  2,  8,  13, 2,
                8,  9,  3,  3,  10, 0};

        // s:  current game state
        R[][]s = new R[6][6];
        for(int i=0;i<6;i++)
            for(int k=0;k<6;k++,v++)
                s[i][k] = new R(r[v]);  // populates state with new Rooms

        s[4][3].e=1; // hard-coded exit
        String n = "\u001B[31m"+"N!"+R; // Red
        while(true) {
            R C = s[x][y];
            // if C(urrent).c(rumbs), add crumbs to player, remove crumbs from state
            if(C.c>0){c+=3;p(G+"\t!C!"+R);s[x][y].c=0;}

            // draw
            for (int i = 0; i < 6; i++) {
                p("\n");
                for (int k = 0; k < 6; k++) {
                    if (y == k && x == i) p(G+"> <"+R);
                    else if (s[i][k].e>0) p(G+"[*]"+R);
                    else if (s[i][k].b>0) p(s[i][k].o);
                    else p("[ ]");
                }
            }

            // if we're at the exit, end
            if (s[x][y].e>0)break;

            Scanner l = new Scanner(System.in);
            p("\n:");
            char a = l.next().charAt(0);

            // test input,
            // drop crumbs in current area if any are left,
            // move player accordingly,
            if("udlr".indexOf(a)>=0)if (c > 0 && s[x][y].b < 1){c--;s[x][y].b =1;}
            if(a=='u'&&C.u>0)x--;
            else if(a=='d'&&C.d>0)x++;
            else if(a=='l'&&C.l>0)y--;
            else if(a=='r'&&C.r>0)y++;
            else p(n);
        }
        p("V!");
    }
}


###  Final  ###
###  w/s removed (\n added to avoid sidescroll)  ###
// Final Char Count:  1000 on the money
import java.util.*;public class G{static void p(Object l){System.out.print(l);}public static void main(String[]b)
{int x=2,y=2,v=0,c=5;String R="\u001B[0m";String G="\u001B[32m";
int[]r={4,1,3,7,6,0,12,5,6,8,13,6,9,14,9,3,15,12,5,11,3,6,12,8,9,3,2,8,13,2,8,1,3,3,10,0};
R[][]s=new R[6][6];for(int i=0;i<6;i++)for(int k=0;k<6;k++,v++)s[i][k]=new R(r[v]);s[4][3].e=1;
String n="\u001B[31m"+"N!"+R;while(true){R C=s[x][y];if(C.c>0){c+=3;p(G+"\t!C!"+R);s[x][y].c=0;}for(int i=0;i<6;i++){p("\n");
for(int k=0;k<6;k++){if(y==k&&x==i)p(G+">|<"+R);else if(s[i][k].e>0)p(G+"{*}"+R);else if(s[i][k].b>0)p(s[i][k].o);
else p("[ ]");}}if(s[x][y].e>0)break;Scanner l=new Scanner(System.in);p("\n:");char a=l.next().charAt(0);
if("udlr".indexOf(a)>=0)if(c>0&&s[x][y].b<1){c--;s[x][y].b=1;}if(a=='u'&&C.u>0)x--;else if(a=='d'&&C.d>0)x++;
else if(a=='l'&&C.l>0)y--;else if(a=='r'&&C.r>0)y++;else p(n);}p("V");}}class R{R(int x){if((x%4)<1)c=1;u=(x>>3)&1;
d=(x>>2)&1;l=(x>>1)&1;r=(x>>0)&1;o="[-]";}int u,d,l,r,b,e,c;String o;}
