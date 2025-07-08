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

    printf("\n\t\t\t\t\       ��ӭʹ��������������ϵͳ\n\n");
    printf("\t\t\t\t----------------------------------------------------\n");
    printf("\t\t\t\t*******************���˵�***************************\n");
    printf("\t\t\t\t\----------------------------------------------------\n");
    printf("\t\t\t\t\  ��ǰ����%d������\n\n", packnum);
    printf("\t\t\t\t1.��Ӱ���       2.���Ұ���\n");
    printf("\t\t\t\t3.�޸İ�����Ϣ   4.ȡ������\n");
    printf("\t\t\t\t5.��ʾ������Ϣ   6.��������������Ϣ\n");
    printf("\t\t\t\t7.����������������ϵͳ  8.�˳�ϵͳ��������Ϣ\n");
}
int Readfile(pack pa[], char filename[])
{

    int a = 0;
    FILE *fp;
    int i;
    fp = fopen(filename, "r");
    if (fp == NULL)
    {

        printf("�ļ�%s���ܴ򿪣�\n", filename);
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
    printf("�������     ȡ����\t    ��������\t��������������\t    ��ϵ�绰\n");
    printf("------------------------------------------------------------------------\n");
    printf("%   -13d%-16s%-16s%-16s%-16s\n", pa.id, pa.code, pa.good, pa.name, pa.tel);
}
void add()
{
    system("cls");
    printf("\t\t�������������Ϣ\n");
    printf("----------------------------------\n");
    while (1)
    {
        if (packnum == 20)
        {
            printf("��������������޷���ӣ�\n");
            return;
        }
        printf("������ȡ����(#������Ӷ���)������������������������ϵ�绰��\n");
        pack pa;
        scanf("%s", pa.code);

        if (strcmp(pa.code, "#") == 0)
        {
            printf("��Ӱ�����Ϣ��������������������˵���");
            break;
        }
        int use;
        do
        {
            use = checkname(pa.code);

            if (!use)
            {
                printf("ȡ���������ظ������������룺");
                scanf("%s", pa.code);
            }
        } while (!use);
        scanf("%s %s %s", pa.good, pa.name, pa.tel);
        packs[packnum] = pa;
        display(pa);
        packnum++;
        printf("������Ϣ�����ɣ�\n");
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
    printf("\n\t\t �޸İ�����Ϣ\n");
    printf("--------------------------------------\n");
    printf("1.�ð���ȡ������в�ѯ��\n");
    printf("2.�ð������ƽ��в�ѯ��\n");
    printf("3.���������в�ѯ\n");
    printf("4.�õ绰�����ѯ\n");
    printf("5.�˳��޸ģ��������˵���\n");
    while (1)
    {
        int opt, iteam;
        do
        {
            printf("���������ѡ��1/2/3/4/5����");

            scanf("%d", &opt);
            fflush(stdin);
        } while (!(opt >= 1 && opt <= 5));

        if (opt == 1)
        {
            printf("������Ҫ��ѯ��ȡ���룺");
            char findid[10000];
            scanf("%s", findid);
            iteam = searchid(findid);
            if (iteam == -1)
            {
                printf("û���ҵ�ȡ���룺[%s]�İ��������ʵȡ���롣\n", findid);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 2)
        {
            printf("������Ҫ��ѯ�İ������ƣ�\n");
            char findname[20];
            scanf("%s", findname);
            iteam = searchname(findname);
            if (iteam == -1)
            {
                printf("û���ҵ����ƣ�[%s]�İ��������ʵ�������ơ�\n", findname);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 3)
        {
            printf("������Ҫ��ѯ��������\n");
            char findleader[PUTCODE];
            scanf("%s", findleader);
            iteam = searchleader(findleader);
            if (iteam == -1)
            {
                printf("û���ҵ�������[%s]�İ��������ʵ�������ơ�\n", findleader);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 4)
        {
            printf("������Ҫ��ѯ�ĵ绰���룺\n");
            char findtel[12];
            scanf("%s", findtel);
            iteam = searchtel(findtel);
            if (iteam == -1)
            {
                printf("û���ҵ��绰�ţ�[%s]�İ��������ʵ�绰���롣\n", findtel);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 5)
        {
            printf("�޸����˳�����������������˵���\n");
            break;
        }

        if (iteam != -1)
        {
            printf("�޸�ǰ\n");
            display(packs[iteam]);
            pack pa;
            printf("�������µ�ȡ���루#�����޸ģ���\n");
            scanf("%s", pa.code);
            if (strcmp(pa.code, "#") == 0)
            {
                printf("�޸İ�����Ϣ��������������������˵���");
                break;
            }
            int use;
            do
            {
                use = checkname(pa.code);
                if (!use)
                {

                    printf("ȡ�����ظ������������룺");
                    scanf("%s", pa.code);
                }
            } while (!use);

            printf("\n��Ʒ���ƣ���Ʒ�����˺���ϵ�绰��\n");
            scanf("%s %s %s", packs[iteam].good, packs[iteam].name, packs[iteam].tel);
            printf("�޸ĺ�\n");
            display(packs[iteam]);
        }
    }
}
void del()
{
    system("cls");
    printf("\n\t\t ɾ��������Ϣ\n");
    printf("--------------------------------------\n");
    printf("1.�ð���ȡ������в�ѯ��\n");
    printf("2.�ð������ƽ��в�ѯ��\n");
    printf("3.���������в�ѯ\n");
    printf("4.�õ绰�����ѯ\n");
    printf("5.�˳�ɾ�����������˵���\n");
    while (1)
    {
        int opt, iteam;
        do
        {
            printf("���������ѡ��1/2/3/4/5����");

            scanf("%d", &opt);
            fflush(stdin);
        } while (!(opt >= 1 && opt <= 5));
        if (opt == 1)
        {
            printf("������Ҫ��ѯ��ȡ���룺");
            char findid[PUTCODE];
            scanf("%s", findid);
            iteam = searchid(findid);
            if (iteam == -1)
            {
                printf("û���ҵ�ȡ���룺[%s]�İ��������ʵȡ���롣\n", findid);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 2)
        {
            printf("������Ҫ��ѯ�İ������ƣ�\n");
            char findname[20];
            scanf("%s", findname);
            iteam = searchname(findname);
            if (iteam == -1)
            {
                printf("û���ҵ����ƣ�[%s]�İ��������ʵ�������ơ�\n", findname);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 3)
        {
            printf("������Ҫ��ѯ��������\n");
            char findleader[20];
            scanf("%s", findleader);
            iteam = searchleader(findleader);
            if (iteam == -1)
            {
                printf("û���ҵ�������[%s]�İ��������ʵ�������ơ�\n", findleader);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 4)
        {
            printf("������Ҫ��ѯ�ĵ绰���룺\n");
            char findtel[12];
            scanf("%s", findtel);
            iteam = searchtel(findtel);
            if (iteam == -1)
            {
                printf("û���ҵ��绰�ţ�[%s]�İ��������ʵ�绰���롣\n", findtel);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 5)
        {
            printf("ȡ�������������˳�����������������˵���\n");
            break;
        }

        if (iteam != -1)
        {

            printf("Ҫȡ���İ�����Ϣ��\n");
            printf("������˶԰�����Ϣ\n");
            display(packs[iteam]);
            printf("\n��ȷ��Ҫȡ��������������1��ȡ����������0��");
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
                printf("����ȡ���ɹ���\n");
            }
            if (k == 0)
            {
                printf("ȡ��������ȡ����\n");
            }
        }
    }
}
void show()
{
    system("cls");
    printf("\n\t\t����������Ϣ\n");
    printf("\nĿǰ�й�%d��������\n", packnum);
    printf("������5��������Ϣһ���ķ�ʽ����ʾ���а�����Ϣ��\n");

    for (int i = 0; i < packnum; i++)
    {

        if (i % 5 == 0)
        {

            system("pause");
            system("cls");
            printf("\n\t\t����������Ϣ\n");
        }
        display(packs[i]);
    }
    printf("\n����ʾ���а�����Ϣ����������������˵���\n");
}
void find()
{
    system("cls");
    printf("\n\t\t ���Ұ��� \n");
    printf("--------------------------------------\n");
    printf("1.�ð���ȡ������в�ѯ��\n");
    printf("2.�ð������ƽ��в�ѯ��\n");
    printf("3.���������в�ѯ\n");
    printf("4.�õ绰�����ѯ\n");
    printf("5.�˳���ѯ���������˵���\n");
    while (1)

    {
        int opt;
        do
        {
            printf("���������ѡ��1/2/3/4/5����");

            scanf("%d", &opt);
            fflush(stdin);
        } while (!(opt >= 1 && opt <= 5));

        int iteam;
        if (opt == 1)
        {
            printf("������Ҫ��ѯ��ȡ���룺");
            char findid[PUTCODE];
            scanf("%s", findid);
            iteam = searchid(findid);
            if (iteam == -1)
            {
                printf("û���ҵ�ȡ���룺[%s]�İ��������ʵȡ���롣\n", findid);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 2)
        {
            printf("������Ҫ��ѯ�İ������ƣ�\n");
            char findname[PUTCODE];
            scanf("%s", findname);
            iteam = searchname(findname);
            if (iteam == -1)
            {
                printf("û���ҵ����ƣ�[%s]�İ��������ʵ�������ơ�\n", findname);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 3)
        {
            printf("������Ҫ��ѯ��������\n");
            char findleader[PUTCODE];
            scanf("%s", findleader);
            iteam = searchleader(findleader);
            if (iteam == -1)
            {
                printf("û���ҵ�������[%s]�İ��������ʵ�������ơ�\n", findleader);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 4)
        {
            printf("������Ҫ��ѯ�ĵ绰���룺\n");
            char findtel[PUTCODE];
            scanf("%s", findtel);
            iteam = searchtel(findtel);
            if (iteam == -1)
            {
                printf("û���ҵ��绰�ţ�[%s]�İ��������ʵ�绰���롣\n", findtel);
            }
            else
            {
                display(packs[iteam]);
            }
        }
        if (opt == 5)
        {
            printf("�������˳�����������������˵���\n");
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
        printf("��ϵͳ�״����У����ڱ��������Ϣ��ϵͳ�����Ƴ����Զ������ļ���\n");
        printf("��ϵͳ�����״����������ݶ�ʧ��\n");
        printf("�밴ϵͳ�˵�Ҫ�󣬱������ݣ�����6���������˳�ϵͳ������8����\n");

        system("pause");
    }
    while (1)
    {
        system("cls");
        showmin();
        int choice;
        do
        {
            printf("\t\t\t\t��ѡ��1/2/3/4/5/6/7/8����");
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
            printf("������Ϣ��ɣ�\n");
            printf("\n��������������˵���\n");
        }
        if (choice == 7)

        {
            system("cls");
            printf("����������������ϵͳ\n");
            printf("����������������ϵͳv3.0plus\n����ߣ���ͬ��\n");
            printf("ϵͳv3.0ʹ�ýṹ�屣�������Ϣ�������Զ���š�\n");
            printf("�԰���������أ���Ϣ�������ݵȹ��ܡ�\n");
            printf("������Ĳ�������ֵ����ӵ�� QAQ");
            printf("\n��������������˵���");
        }
        if (packnum == 0 && (choice == 2 || choice == 3 || choice == 4))
        {
            system("cls");
            printf("��ǰ��������Ϊ0��������Ӱ�����o.O\n");

            printf("��������������˵���");
        }

        if (choice == 8)
        {
            system("cls");
            writefile(packs, "pack.txt");
            printf("\t\t���Զ�������Ϣ\n");
            printf("\t\t�˳�ϵͳ\n\n\t\t��ӭ�´�ʹ�ã�\n");
            break;
        }
    }

    return 0;
}