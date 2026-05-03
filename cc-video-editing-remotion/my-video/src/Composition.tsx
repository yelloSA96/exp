import { AbsoluteFill } from "remotion";
import {
  TransitionSeries,
  linearTiming,
} from "@remotion/transitions";
import { fade } from "@remotion/transitions/fade";
import { VideoScene } from "./components/VideoScene";
import { IntroCard } from "./components/IntroCard";
import { OutroCard } from "./components/OutroCard";

export interface VideoClip {
  src: string;
  message: string;
  durationInFrames: number;
}

export interface MyCompositionProps extends Record<string, unknown> {
  videos: VideoClip[];
}

const TRANSITION_FRAMES = 15;

export const MyComposition: React.FC<MyCompositionProps> = ({ videos }) => {
  return (
    <AbsoluteFill>
      <TransitionSeries>
        {/* Intro card */}
        <TransitionSeries.Sequence durationInFrames={60} premountFor={30}>
          <IntroCard />
        </TransitionSeries.Sequence>
        <TransitionSeries.Transition
          presentation={fade()}
          timing={linearTiming({ durationInFrames: TRANSITION_FRAMES })}
        />

        {/* Video clips with fade transitions between them */}
        {videos.flatMap((clip, i) => [
          <TransitionSeries.Sequence
            key={`clip-${i}`}
            durationInFrames={clip.durationInFrames}
            premountFor={30}
          >
            <VideoScene src={clip.src} message={clip.message} />
          </TransitionSeries.Sequence>,
          ...(i < videos.length - 1
            ? [
                <TransitionSeries.Transition
                  key={`transition-${i}`}
                  presentation={fade()}
                  timing={linearTiming({ durationInFrames: TRANSITION_FRAMES })}
                />,
              ]
            : []),
        ])}

        {/* Outro card */}
        <TransitionSeries.Transition
          presentation={fade()}
          timing={linearTiming({ durationInFrames: TRANSITION_FRAMES })}
        />
        <TransitionSeries.Sequence durationInFrames={90} premountFor={30}>
          <OutroCard />
        </TransitionSeries.Sequence>
      </TransitionSeries>
    </AbsoluteFill>
  );
};
