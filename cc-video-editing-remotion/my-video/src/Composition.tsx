import { Fragment } from "react";
import { CalculateMetadataFunction, Composition } from "remotion";
import { TransitionSeries, linearTiming } from "@remotion/transitions";
import { fade } from "@remotion/transitions/fade";
import { AbsoluteFill, Sequence } from "remotion";
import { SHOTS } from "./data/shots";
import { MediaShot } from "./components/MediaShot";
import { TitleCard } from "./components/TitleCard";
import { CreditsRoll } from "./components/CreditsRoll";
import { FilmGrain } from "./components/FilmGrain";

const FPS = 30;
const WIDTH = 1920;
const HEIGHT = 1080;

const INTRO_DURATION = 100;
const OUTRO_DURATION = 130;
const TRANSITION_DURATION = 16;

const numberOfTransitions = SHOTS.length + 1; // intro->first shot, between shots, last shot->outro
const mediaDuration = SHOTS.reduce((sum, s) => sum + s.durationInFrames, 0);

export const TOTAL_DURATION =
  INTRO_DURATION +
  mediaDuration +
  OUTRO_DURATION -
  numberOfTransitions * TRANSITION_DURATION;

const CREDITS_START = INTRO_DURATION - TRANSITION_DURATION;
const CREDITS_DURATION =
  TOTAL_DURATION - INTRO_DURATION - OUTRO_DURATION + 2 * TRANSITION_DURATION;

type Props = {};

const calculateMetadata: CalculateMetadataFunction<Props> = () => {
  return {
    durationInFrames: TOTAL_DURATION,
    fps: FPS,
    width: WIDTH,
    height: HEIGHT,
  };
};

export const MyComposition = () => {
  return (
    <Composition
      id="AnimeEnding"
      component={MyComponent}
      durationInFrames={TOTAL_DURATION}
      fps={FPS}
      width={WIDTH}
      height={HEIGHT}
      calculateMetadata={calculateMetadata}
    />
  );
};

export const MyComponent: React.FC<Props> = () => {
  return (
    <AbsoluteFill style={{ backgroundColor: "#000" }}>
      <TransitionSeries>
        <TransitionSeries.Sequence durationInFrames={INTRO_DURATION}>
          <TitleCard
            durationInFrames={INTRO_DURATION}
            eyebrow="a trip to"
            title="Glass House Mountains"
            subtitle="14 September 2022"
          />
        </TransitionSeries.Sequence>

        {SHOTS.map((shot, i) => (
          <Fragment key={i}>
            <TransitionSeries.Transition
              presentation={fade()}
              timing={linearTiming({ durationInFrames: TRANSITION_DURATION })}
            />
            <TransitionSeries.Sequence
              durationInFrames={shot.durationInFrames}
            >
              <MediaShot shot={shot} />
            </TransitionSeries.Sequence>
          </Fragment>
        ))}

        <TransitionSeries.Transition
          presentation={fade()}
          timing={linearTiming({ durationInFrames: TRANSITION_DURATION })}
        />
        <TransitionSeries.Sequence durationInFrames={OUTRO_DURATION}>
          <TitleCard
            durationInFrames={OUTRO_DURATION}
            eyebrow="thank you for"
            title="the memories"
            subtitle="til next time"
          />
        </TransitionSeries.Sequence>
      </TransitionSeries>

      <Sequence from={CREDITS_START} durationInFrames={CREDITS_DURATION}>
        <CreditsRoll totalDurationInFrames={CREDITS_DURATION} />
      </Sequence>

      <FilmGrain />
    </AbsoluteFill>
  );
};
