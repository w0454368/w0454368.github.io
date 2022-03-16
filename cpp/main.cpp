#include <iostream>
#include <fstream>
#include <conio.h>
#include <cstdlib>
//#include <unistd.h>
#include <Windows.h>
//#include <dos.h>
#include <iomanip>


using namespace std;

float static kmRate=0.50,car1Rate=56.0,car2Rate=60.0,car3Rate=75.0;

class admin {
public:

    int select() {
        int select;
            cout << "\t\t\t\tWelcome to the Admin menu. Please select an option below:" << endl;
            cout << "\n\t\t\t\t1: Adjust car rental prices." << endl;
            cout << "\t\t\t\t2: Adjust fee per Km traveled." << endl;
            cout << "\t\t\t\t3: Log out." << endl;
            cin >> select;
            switch (select) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                default:
                    cout << "\t\t\t\tInvalid selection. Please try again." << endl;
            }

    }

    void adjustCarRate() {
        int select;
        float newRate;
        cout << "\t\t\t\tPlease select a car to modify the per day rental price of:" << endl;
        cout << "\n\t\t\t\tEnter '1' for Tesla 20011." << endl;
        cout << "\t\t\t\tEnter '2' for Hyundai 2015." << endl;
        cout << "\t\t\t\tEnter '3' for Ford 2017." << endl;
        cin >> select;
        switch (select) {
            case 1:
                cout << "Please enter the new rate for this car:" << endl;
                cin >> newRate;
                car1Rate = newRate;
                break;
            case 2:
                cout << "Please enter the new rate for this car:" << endl;
                cin >> newRate;
                car2Rate = newRate;
                break;
            case 3:
                cout << "Please enter the new rate for this car:" << endl;
                cin >> newRate;
                car3Rate = newRate;
                break;
            default:
                cout << "Invalid selection. Please try again." << endl;
                this->adjustCarRate();
        }
    }

    void adjustKmRate() {
        float newRate;
        cout << "Please enter new fee per Km traveled:" << endl;
        cin >> newRate;
        kmRate = newRate;
    }

};

class customer // customer class
{
private:
public:
    string customername;
    string carmodel;
    char data;
    // variables defined in this class in public mode.
};
class rent : public customer // inhereted class from customer class
{
public:
    int days=0; // additional int vatiables defined
    float rentalfee=0.00,rentDist=0.0;
    void data()
    {
        cout << "\t\t\t\tPlease Enter your Name: "; //taking data from the user
        cin >> customername;
        cout<<endl;
        do
        {
            cout <<"\t\t\t\tPlease Select a Car"<<endl; //giving user a choice to select among three different models
            cout<<"\t\t\t\tEnter '1' for Tesla 2011."<<endl;
            cout<<"\t\t\t\tEnter '2' for Hyundai 2015."<<endl;
            cout<<"\t\t\t\tEnter '3' for Ford 2017."<<endl;
            cout<<endl;
            cout<<"\t\t\t\tChoose a Car from the above options: ";
            cin >>carmodel;
            cout<<endl;
            cout<<"--------------------------------------------------------------------------"<<endl;
            if(carmodel=="1")
            {
                system("CLS");

                cout<<"You have chosen Tesla model 2011"<<endl;
                ifstream in1("1.txt"); //displaying details of model 1
                char str[200];
                while(in1) {
                    in1.getline(str, 200);
                    if(in1) cout << str << endl;
                }
                Sleep(2);
            }
            if(carmodel=="2")
            {
                system("CLS");

                cout<<"You have chosen Hyundai model 2015"<<endl;
                ifstream in2("2.txt"); //displaying details of model 2
                char str[200];
                while(in2) {
                    in2.getline(str, 200);
                    if(in2) cout << str << endl;

                }
                Sleep(2);
            }
            if(carmodel=="3")
            {
                system("CLS");
                cout<<"You have chosen Ford model 2017"<<endl;
                ifstream in3("3.txt"); //displaying details of model 3
                char str[200];
                while(in3) {
                    in3.getline(str, 200);
                    if(in3) cout << str << endl;
                }
                Sleep(2);
            }
            if(carmodel !="1" && carmodel !="2" &&  carmodel !="3" )

                cout<<"Invaild Car Model. Please try again!"<<endl;
        }
        while(carmodel !="1" && carmodel !="2" &&  carmodel !="3" );
        cout<<"--------------------------------------------------------------------------"<<endl;
        cout << "Please provide following information: "<<endl;
        //getting data from user related to rental service
        cout<<"Number of days you wish to rent the car : ";
        cin >> days;
        cout << "How many kilometers to your destination : ";
        cin >> rentDist;
        cout<<endl;
    }
    void calculate()
    {
        Sleep(1000);
        system ("CLS");
        cout<<"Calculating rent. Please wait......"<<endl;
        Sleep(2000);
        if(carmodel == "1")
            rentalfee=(days*car1Rate)+(rentDist*kmRate);
        if(carmodel == "2")
            rentalfee=(days*car2Rate)+(rentDist*kmRate);
        if(carmodel == "3")
            rentalfee=(days*car3Rate)+(rentDist*kmRate);
    }
    void showrent()
    {
        cout << "\n\t\t                       Car Rental - Customer Invoice                  "<<endl;
        cout << "\t\t	///////////////////////////////////////////////////////////"<<endl;
        cout << "\t\t	| Customer Name:"<<"-----------------|"<<setw(10)<<customername<<" |"<<endl;
        cout << "\t\t	| Car Model :"<<"--------------------|"<<setw(10)<<carmodel<<" |"<<endl;
        cout << "\t\t	| Number of days :"<<"---------------|"<<setw(10)<<days<<" |"<<endl;
        cout << "\t\t        | Distance to destination :"<<"------|"<<setw(10)<<rentDist<<" |"<<endl;
        cout << "\t\t        | Fee per KM traveled :"<<"----------|"<<setw(10)<<kmRate<<" |"<<endl;
        cout << "\t\t	 ________________________________________________________"<<endl;
        cout <<"\n";
        cout << "\t\t	| Total Rental Amount is :"<<"-------|"<<setw(10)<<"$"<<fixed<<setprecision(2)<<rentalfee<<" |"<<endl;
        cout << "\t\t	 ________________________________________________________"<<endl;

        int f;
        system("PAUSE");

        system ("CLS");
    }
};
int login();
int main()
{
    rent obj2;  //object created for rent class and further member functions are called
    admin obj1;
    int loop = 1;
    do {
        int passCode = login();
        switch (passCode) {
            case 0:
                obj2.data();
                obj2.calculate();
                obj2.showrent();
                break;
            case 1:
            {int adminLoop = 1;
                do {
                    int menu = obj1.select();
                    switch (menu) {
                        case 1:
                            obj1.adjustCarRate();
                            break;
                        case 2:
                            obj1.adjustKmRate();
                            break;
                        case 3:
                            adminLoop = 0;
                            break;
                    }
                } while (adminLoop != 0);}
                break;
            case 2:
                cout << "\t\t\t\tTry again?" << endl;
                cout << "\n\t\t\t\tType 'Y' for yes, anything else for no" << endl;
                char select;
                cin >> select;
                if (select != 'Y' && select != 'y') {
                    loop = 0;
                }
        }
    } while (loop != 0);
    return 0; //end of the program
}

int login(){
    string pass ="";
    char ch;
    cout<<"\n\n\n\n\n\n\n\n\t\t\t\t\t       CAR RENTAL SYSTEM \n\n";
    cout<<"\t\t\t\t\t------------------------------";
    cout<<"\n\t\t\t\t\t\t     LOGIN \n";
    cout<<"\t\t\t\t\t------------------------------\n\n";
    cout << "\t\t\t\t\tEnter Password: ";
    ch = _getch();
    while(ch != 13){//character 13 is enter
        pass.push_back(ch);
        cout << '*';
        ch = _getch();
    }
    if(pass == "pass") {
        cout << "\n\n\n\t\t\t\t\t\tAccess Granted! \n";
        system("PAUSE");
        system("CLS");
        return 0;
    } else if(pass == "admin") {
        cout << "\n\n\n\t\t\t\t\t\tAdmin Access Granted! \n";
        system("PAUSE");
        system("CLS");
        return 1;
    }else{
        cout << "\n\n\t\t\t\t\t\t\tInvalid login..." << endl;
        system("PAUSE");
        system("CLS");
        return 2;
    }
}
