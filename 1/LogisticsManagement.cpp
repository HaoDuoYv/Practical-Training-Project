#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TOLE 100
#define PUTCODE 10000
#define NAME_MAX 20
#define TEL_MAX 12
#define GOOD_NAME_MAX 20
typedef struct
{
    int id;
    char code[PUTCODE];
    char name[NAME_MAX];
    char good[GOOD_NAME_MAX];
    char tel[TEL_MAX];

} pack;
pack packs[TOLE];

int packnum = 0;

void showmin()
{

    printf("\n\t\t\t\t\       欢迎使用物流包裹管理系统\n\n");
    printf("\t\t\t\t----------------------------------------------------\n");
    printf("\t\t\t\t*******************主菜单***************************\n");
    printf("\t\t\t\t\----------------------------------------------------\n");
    printf("\t\t\t\t\  当前共有%d个包裹\n\n", packnum);
    printf("\t\t\t\t1.添加包裹       2.查找包裹\n");
    printf("\t\t\t\t3.修改包裹信息   4.取出包裹\n");
    printf("\t\t\t\t5.显示包裹信息   6.保存物流包裹信息\n");
    printf("\t\t\t\t7.关于物流包裹管理系统  8.退出系统并保存信息\n");
}
int Readfile(pack pa[], char filename[])
{

    int a = 0;
    FILE *fp;
    int i;
    fp = fopen(filename, "r");
    if (fp == NULL)
    {

        printf("文件%s不能打开！\n", filename);
        return -1;
    }
    fscanf(fp, "%d", &a);
    for (i = 0; i < a; i++)
    {
        fscanf(fp, "%d\n", &pa[i].id);
        fscanf(fp, "%s %s %s %s", pa[i].code, pa[i].good, pa[i].name, pa[i].tel);
    }
    fclose(fp);
    return a;
}
void writefile(pack tm[], char filename[])
{
    FILE *fp;
    int i;
    fp = fopen(filename, "w");
    fprintf(fp, "%d\n", packnum);
    for (i = 0; i < packnum; i++)
    {
        fprintf(fp, "%d\n", tm[i].id);
        fprintf(fp, "%s %s %s %s", tm[i].code, tm[i].good, tm[i].name, tm[i].tel);
    }
    fclose(fp);
}
int crateid()
{

    int newid = packs[packnum - 1].id + 1;
    return newid;
}
int checkname(char pname[])
{
    int ans = 1;
    int i = 0;
    for (i = 0; i < packnum; i++)
    {
        if (strcmp(packs[i].code, pname) == 0)
        {

            ans = 0;
            break;
        }
    }
    return ans;
}
void display(pack pa)
{
    printf("包裹编号     取件码\t    包裹名称\t包裹所属人姓名\t    联系电话\n");
    printf("------------------------------------------------------------------------\n");
    printf("%   -13d%-16s%-16s%-16s%-16s\n", pa.id, pa.code, pa.good, pa.name, pa.tel);
}
void add()
{
    system("cls");
    printf("\t\t添加物流包裹信息\n");
    printf("----------------------------------\n");
    while (1)
    {
        if (packnum == 20)
        {
            printf("包裹容量已满额，无法添加！\n");
            return;
        }
        printf("请输入取件码(#结束添加队伍)，包裹名。所属人姓名和联系电话：\n");
        pack pa;
        scanf("%s", pa.code);

        if (strcmp(pa.code, "#") == 0)
        {
            printf("添加包裹信息结束，按任意键返回主菜单！");
            break;
        }
        int use;
        do
        {
            use = checkname(pa.code);

            if (!use)
            {
                printf("取件码名称重复，请重新输入：");
                scanf("%s", pa.code);
            }
        } while (!use);
        scanf("%s %s %s", pa.good, pa.name, pa.tel);
        packs[packnum] = pa;
        display(pa);
        packnum++;
        printf("包裹信息添加完成！\n");
    }
}
int searchtel(char TEL[])
{
    int a = -1;
    for (int i = 0; i < packnum; i++)
    {
        if (strcmp(packs[i].tel, TEL) == 0)
        {
            a = i;
            break;
        }
    }
    return a;
}

int searchid(char code[])
{
    int a = -1;
    for (int i = 0; i < packnum; i++)
    {
        if (strcmp(packs[i].code, code) == 0)
        {
            a = i;
            break;
        }
    }
    return a;
}
int searchname(char name[])
{
    int a = -1;
    for (int i = 0; i < packnum; i++)
    {
        if (strcmp(packs[i].good, name) == 0)
        {
            a = i;
            break;
        }
    }
    return a;
}
int searchleader(char leader[])
{
    int a = -1;
    for (int i = 0; i < packnum; i++)
    {
        if (strcmp(packs[i].name, leader) == 0)
        {
            a = i;
            break;
        }
    }
    return a;
}

void update()
{
    system("cls");
    printf("\n\t\t 修改包裹信息\n");
    printf("--------------------------------------\n");
    printf("1.用包裹取件码进行查询：\n");
    printf("2.用包裹名称进行查询：\n");
    printf("3.用姓名进行查询\n");
    printf("4.用电话号码查询\n");
    printf("5.退出修改，返回主菜单：\n");
    while (1)
    {
        int opt, iteam;
        do
        {
            printf("请输入你的选择【1/2/3/4/5】：");

            scanf("%d", &opt);
            fflush(stdin);
        } while (!(opt >= 1 && opt <= 5));

        if (opt == 1)
        {
            printf("请输入要查询的取件码：");
            char findid[10000];
            scanf("%s", findid);
            iteam = searchid(findid);
            if (iteam == -1)
            {
                printf("没有找到取件码：[%s]的包裹，请核实取件码。\n", findid);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 2)
        {
            printf("请输入要查询的包裹名称：\n");
            char findname[20];
            scanf("%s", findname);
            iteam = searchname(findname);
            if (iteam == -1)
            {
                printf("没有找到名称：[%s]的包裹，请核实包裹名称。\n", findname);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 3)
        {
            printf("请输入要查询的姓名：\n");
            char findleader[PUTCODE];
            scanf("%s", findleader);
            iteam = searchleader(findleader);
            if (iteam == -1)
            {
                printf("没有找到姓名：[%s]的包裹，请核实队伍名称。\n", findleader);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 4)
        {
            printf("请输入要查询的电话号码：\n");
            char findtel[12];
            scanf("%s", findtel);
            iteam = searchtel(findtel);
            if (iteam == -1)
            {
                printf("没有找到电话号：[%s]的包裹，请核实电话号码。\n", findtel);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 5)
        {
            printf("修改已退出，按任意键返回主菜单。\n");
            break;
        }

        if (iteam != -1)
        {
            printf("修改前\n");
            display(packs[iteam]);
            pack pa;
            printf("请输入新的取件码（#结束修改）：\n");
            scanf("%s", pa.code);
            if (strcmp(pa.code, "#") == 0)
            {
                printf("修改包裹信息结束，按任意键返回主菜单！");
                break;
            }
            int use;
            do
            {
                use = checkname(pa.code);
                if (!use)
                {

                    printf("取件码重复，请重新输入：");
                    scanf("%s", pa.code);
                }
            } while (!use);

            printf("\n物品名称，物品所属人和联系电话：\n");
            scanf("%s %s %s", packs[iteam].good, packs[iteam].name, packs[iteam].tel);
            printf("修改后\n");
            display(packs[iteam]);
        }
    }
}
void del()
{
    system("cls");
    printf("\n\t\t 删除包裹信息\n");
    printf("--------------------------------------\n");
    printf("1.用包裹取件码进行查询：\n");
    printf("2.用包裹名称进行查询：\n");
    printf("3.用姓名进行查询\n");
    printf("4.用电话号码查询\n");
    printf("5.退出删除，返回主菜单：\n");
    while (1)
    {
        int opt, iteam;
        do
        {
            printf("请输入你的选择【1/2/3/4/5】：");

            scanf("%d", &opt);
            fflush(stdin);
        } while (!(opt >= 1 && opt <= 5));
        if (opt == 1)
        {
            printf("请输入要查询的取件码：");
            char findid[PUTCODE];
            scanf("%s", findid);
            iteam = searchid(findid);
            if (iteam == -1)
            {
                printf("没有找到取件码：[%s]的包裹，请核实取件码。\n", findid);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 2)
        {
            printf("请输入要查询的包裹名称：\n");
            char findname[20];
            scanf("%s", findname);
            iteam = searchname(findname);
            if (iteam == -1)
            {
                printf("没有找到名称：[%s]的包裹，请核实包裹名称。\n", findname);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 3)
        {
            printf("请输入要查询的姓名：\n");
            char findleader[20];
            scanf("%s", findleader);
            iteam = searchleader(findleader);
            if (iteam == -1)
            {
                printf("没有找到姓名：[%s]的包裹，请核实队伍名称。\n", findleader);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 4)
        {
            printf("请输入要查询的电话号码：\n");
            char findtel[12];
            scanf("%s", findtel);
            iteam = searchtel(findtel);
            if (iteam == -1)
            {
                printf("没有找到电话号：[%s]的包裹，请核实电话号码。\n", findtel);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 5)
        {
            printf("取出包裹操作已退出，按任意键返回主菜单。\n");
            break;
        }

        if (iteam != -1)
        {

            printf("要取出的包裹信息：\n");
            printf("请认真核对包裹信息\n");
            display(packs[iteam]);
            printf("\n你确定要取出包裹吗？请输入1；取消，请输入0；");
            int k;

            scanf("%d", &k);
            fflush(stdin);

            if (k == 1)
            {
                int j = iteam;
                for (j; j < packnum - 1; j++)
                {

                    packs[j] = packs[j + 1];
                }
                packnum--;
                printf("包裹取出成功！\n");
            }
            if (k == 0)
            {
                printf("取出包裹已取消！\n");
            }
        }
    }
}
void show()
{
    system("cls");
    printf("\n\t\t物流包裹信息\n");
    printf("\n目前有共%d个包裹。\n", packnum);
    printf("下面以5个包裹信息一屏的方式，显示所有包裹信息！\n");

    for (int i = 0; i < packnum; i++)
    {

        if (i % 5 == 0)
        {

            system("pause");
            system("cls");
            printf("\n\t\t物流包裹信息\n");
        }
        display(packs[i]);
    }
    printf("\n已显示所有包裹信息，按任意键返回主菜单！\n");
}
void find()
{
    system("cls");
    printf("\n\t\t 查找包裹 \n");
    printf("--------------------------------------\n");
    printf("1.用包裹取件码进行查询：\n");
    printf("2.用包裹名称进行查询：\n");
    printf("3.用姓名进行查询\n");
    printf("4.用电话号码查询\n");
    printf("5.退出查询，返回主菜单：\n");
    while (1)

    {
        int opt;
        do
        {
            printf("请输入你的选择【1/2/3/4/5】：");

            scanf("%d", &opt);
            fflush(stdin);
        } while (!(opt >= 1 && opt <= 5));

        int iteam;
        if (opt == 1)
        {
            printf("请输入要查询的取件码：");
            char findid[PUTCODE];
            scanf("%s", findid);
            iteam = searchid(findid);
            if (iteam == -1)
            {
                printf("没有找到取件码：[%s]的包裹，请核实取件码。\n", findid);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 2)
        {
            printf("请输入要查询的包裹名称：\n");
            char findname[PUTCODE];
            scanf("%s", findname);
            iteam = searchname(findname);
            if (iteam == -1)
            {
                printf("没有找到名称：[%s]的包裹，请核实包裹名称。\n", findname);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 3)
        {
            printf("请输入要查询的姓名：\n");
            char findleader[PUTCODE];
            scanf("%s", findleader);
            iteam = searchleader(findleader);
            if (iteam == -1)
            {
                printf("没有找到姓名：[%s]的包裹，请核实队伍名称。\n", findleader);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 4)
        {
            printf("请输入要查询的电话号码：\n");
            char findtel[PUTCODE];
            scanf("%s", findtel);
            iteam = searchtel(findtel);
            if (iteam == -1)
            {
                printf("没有找到电话号：[%s]的包裹，请核实电话号码。\n", findtel);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 5)
        {
            printf("查找已退出，按任意键返回主菜单。\n");
            break;
        }
    }
}
int main()
{
    packnum = Readfile(packs, "pack.txt");
    if (packnum == -1)
    {
        packnum = 0;
        printf("若系统首次运行，则在保存队伍信息或系统正常推出后自动创建文件。\n");
        printf("若系统不是首次运行则数据丢失。\n");
        printf("请按系统菜单要求，保存数据（输入6）或正常退出系统（输入8）！\n");

        system("pause");
    }
    while (1)
    {
        system("cls");
        showmin();
        int choice;
        do
        {
            printf("\t\t\t\t请选择【1/2/3/4/5/6/7/8】：");
            scanf("%d", &choice);
            fflush(stdin);
        } while (!(choice >= 1 && choice <= 8));
        if (choice == 1)
        {
            add();
        }
        if (choice == 2 && packnum != 0)
        {
            find();
        }
        if (choice == 3 && packnum != 0)
        {
            update();
        }
        if (choice == 4 && packnum != 0)
        {
            del();
        }
        if (choice == 5)
        {
            show();
        }
        if (choice == 6)
        {

            system("cls");
            writefile(packs, "pack.txt");
            printf("保存信息完成！\n");
            printf("\n按任意键返回主菜单！\n");
        }
        if (choice == 7)

        {
            system("cls");
            printf("关于物流包裹管理系统\n");
            printf("关于物流包裹管理系统v3.0plus\n设计者：丁同坤\n");
            printf("系统v3.0使用结构体保存包裹信息，更新自动编号。\n");
            printf("对包裹号码查重，信息保存数据等功能。\n");
            printf("更方便的操作体验值得你拥有 QAQ");
            printf("\n按任意键返回主菜单！");
        }
        if (packnum == 0 && (choice == 2 || choice == 3 || choice == 4))
        {
            system("cls");
            printf("当前包裹总量为0，请先添加包裹！o.O\n");

            printf("按任意键返回主菜单！");
        }

        if (choice == 8)
        {
            system("cls");
            writefile(packs, "pack.txt");
            printf("\t\t已自动保存信息\n");
            printf("\t\t退出系统\n\n\t\t欢迎下次使用！\n");
            break;
        }
    }

    return 0;
}