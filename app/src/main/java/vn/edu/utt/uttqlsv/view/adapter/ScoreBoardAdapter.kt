package vn.edu.utt.uttqlsv.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.model.ScoreBoard

class ScoreBoardAdapter(var scoreBoard: ScoreBoard, var context: Context?) :
    RecyclerView.Adapter<ScoreBoardAdapter.ViewHolder>() {
    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectNameTV: TextView = itemView.findViewById(R.id.subject_name_tv)
        val scoreTV: TextView = itemView.findViewById(R.id.score_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.score_board_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.subjectNameTV.text = "Math"
                holder.scoreTV.text = scoreBoard.mathScore.toString()
            }
            1 -> {
                holder.subjectNameTV.text = "Literature"
                holder.scoreTV.text = scoreBoard.literatureScore.toString()
            }
            2 -> {
                holder.subjectNameTV.text = "English"
                holder.scoreTV.text = scoreBoard.englishScore.toString()
            }
            3 -> {
                holder.subjectNameTV.text = "Physic"
                holder.scoreTV.text = scoreBoard.physicScore.toString()
            }
            4 -> {
                holder.subjectNameTV.text = "Chemistry"
                holder.scoreTV.text = scoreBoard.chemistryScore.toString()
            }
            5 -> {
                holder.subjectNameTV.text = "History"
                holder.scoreTV.text = scoreBoard.historyScore.toString()
            }
            6 -> {
                holder.subjectNameTV.text = "Geography"
                holder.scoreTV.text = scoreBoard.geographyScore.toString()
            }
            7 -> {
                holder.subjectNameTV.text = "Biology"
                holder.scoreTV.text = scoreBoard.biologyScore.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return 8
    }
}