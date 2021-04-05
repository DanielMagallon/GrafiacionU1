package Perfomance;

public class Obstaculos
{
    public static int [][] ghostPosition =
            {
                    {1,4,0,2},
                    {2,6,0,5},
                    {4,0,3,6},
                    {4,3,2,2},
                    {6,1,5,5},
                    {6,4,4,5}
            };

    public static int [][] pipePosition =
            {
                    {0,4,1,3},
                    {1,6,2,3},
                    {3,0,4,1},
                    {4,6,6,0}
            };


    public static boolean onGhost(int row, int col){
        for(int posObs[] : ghostPosition)
        {
            if(posObs[0]==row && posObs[1]==col)
                return true;
        }

        return false;
    }



    public static int[] check(int[] pos){

        for(int posObs[] : ghostPosition)
        {
            if(posObs[0]==pos[0] && posObs[1]==pos[1])
                return new int[]{-1,posObs[2],posObs[3]};
        }

        for(int posObs[] : pipePosition)
        {
            if(posObs[0]==pos[0] && posObs[1]==pos[1])
                return new int[]{1,posObs[2],posObs[3]};
        }

        return null;
    }
}
